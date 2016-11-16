package schedule;

import analysis.Stats;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

import static java.util.Collections.unmodifiableList;

/**
 * Fantasy Schedule for one team
 */
public class FantasySchedule {

    public static final String[] ALL_OPPONENTS = {"Alex", "Bilal", "Ryan", "Fred"};

    public static final int NUM_GAMES_PER_SEASON = 14;
    public static final int MAX_HEAD_TO_HEAD_GAMES = 5;
    public static final int START_OF_SEASON = 1;

    // Team records
    public static final double[] ALEX2015 = {135.34, 126.14, 140.82, 101.48, 104.80, 153.16, 169.12, 136.38, 144.82
            , 121.28, 103.36, 101.86, 133.78, 138.24};
    public static final double[] BILAL2015 = {127.00, 94.16, 139.00, 91.12, 147.58, 125.16, 163.92, 120.82, 141.86,
            133.36, 60.30, 104.02, 171.00, 174.50};
    public static final double[] RYAN2015 = {125.22, 95.82, 157.14, 137.62, 139.80, 153.64, 109.48, 153.18, 109.20,
            86.86, 123.80, 79.46, 150.88, 127.20};
    public static final double[] FRED2015 = {126.44, 115.74, 176.22, 114.62, 114.62, 121.50, 95.90, 111.74, 144.84,
            132.44, 102.80, 119.64, 104.08, 116.74};

    private HashMap<Integer, String> currentSchedule;

    public FantasySchedule() {
        currentSchedule = new HashMap<>();
    }

    public FantasySchedule(FantasySchedule current) {
        this.currentSchedule = current.getSchedule();
    }

    public static Set<FantasySchedule> createSchdules(String teamName) {

        List<String> modOpponents = new ArrayList<>(Arrays.asList(ALL_OPPONENTS));
        modOpponents.remove(teamName);
        List<String> opponents = Collections.unmodifiableList(modOpponents);

        // Set for possible schedules
        final Set<FantasySchedule> finalSchedule = new HashSet<>();

        // Empty {@link FantasySchedule} to use as root
        FantasySchedule current = new FantasySchedule();
        if (current.getNumGames() < NUM_GAMES_PER_SEASON) {
            createSchedulesRecursive(new FantasySchedule(current.addGame(START_OF_SEASON, opponents.get(0))),
                    START_OF_SEASON, finalSchedule, opponents);
            createSchedulesRecursive(new FantasySchedule(current.addGame(START_OF_SEASON, opponents.get(1))),
                    START_OF_SEASON, finalSchedule, opponents);
            createSchedulesRecursive(new FantasySchedule(current.addGame(START_OF_SEASON, opponents.get(2))),
                    START_OF_SEASON, finalSchedule, opponents);
        }
        return finalSchedule;
    }

    private static void createSchedulesRecursive(FantasySchedule current, int week, Set<FantasySchedule> schedule,
                                                 List<String> opponents) {
        week++;

        int team0 = 0;
        int team1 = 0;
        int team2 = 0;

        HashMap<Integer, String> sched = current.getSchedule();
        for (Integer i : sched.keySet()) {
            if ( sched.get(i).equals(opponents.get(0)) ) {
                team0++;
            } else if ( sched.get(i).equals(opponents.get(0)) ) {
                team1++;
            } else if ( sched.get(i).equals(opponents.get(0)) ) {
                team2++;
            }
        }

        if (current.getNumGames() < NUM_GAMES_PER_SEASON) {
            if (team0 < MAX_HEAD_TO_HEAD_GAMES) {
                createSchedulesRecursive(new FantasySchedule(current.addGame(week, opponents.get(0))),
                        week, schedule, opponents);
            }
            if (team1 < MAX_HEAD_TO_HEAD_GAMES) {
                createSchedulesRecursive(new FantasySchedule(current.addGame(week, opponents.get(1))),
                        week, schedule, opponents);
            }
            if (team2 < MAX_HEAD_TO_HEAD_GAMES) {
                createSchedulesRecursive(new FantasySchedule(current.addGame(week, opponents.get(2))),
                        week, schedule, opponents);
            }
        } else {
            schedule.add(current);
            System.out.println(schedule.size());
        }
    }

    public static void main(String[] args) {

        System.out.println("Creating all possible schedules");
        long t = System.currentTimeMillis();

        // Create all possible schedules
        Set<FantasySchedule> alexFinalSet = createSchdules("Alex");
        int currentTime = (int) (System.currentTimeMillis() - t) / 1000;
        System.out.println("Total time to create schedules: " + currentTime);
        System.out.println("There are a total of " + alexFinalSet.size() + " possible schedules\n");

        // Print results
        int possibleSchedules = alexFinalSet.size();
        int[] alexWins = Stats.getWinsDistribution(alexFinalSet, "Alex");
        printDistribution("Alex", alexWins, possibleSchedules);

        Set<FantasySchedule> ryanFinalSet = createSchdules("Ryan");
        int[] ryanWins = Stats.getWinsDistribution(ryanFinalSet, "Ryan");
        printDistribution("Ryan", ryanWins, possibleSchedules);

        Set<FantasySchedule> bilalFinalSet = createSchdules("Bilal");
        int[] bilalWins = Stats.getWinsDistribution(bilalFinalSet, "Bilal");
        printDistribution("Bilal", bilalWins, possibleSchedules);

        Set<FantasySchedule> fredFinalSet = createSchdules("Fred");
        int[] fredWins = Stats.getWinsDistribution(fredFinalSet, "Fred");
        printDistribution("Fred", fredWins, possibleSchedules);
    }

    private static void printDistribution(String teamName, int[] wins, int possibleScheds) {
        for (int i = 0; i < NUM_GAMES_PER_SEASON + 1; i++) {
            double percent = (double) wins[i] / (double) possibleScheds * 100.0;
            System.out.println(teamName +  " had " + i + " wins " + wins[i] + " times!");
            System.out.print(teamName + " had " + i + " wins ");
            System.out.format("%.1f", percent);
            System.out.println(" percent of the time!\n");
        }
    }

    /**
     * Creates a team with their scores for the season
     * @param name is team name
     * @param scores for the entire season
     * @return Team with scores
     */
    private static Team createTeam(String name, double[] scores) {
        Team team = new Team(name);

        // Add a game for each week
        for (int i = 0; i < NUM_GAMES_PER_SEASON; i++) {
            team.addGame(i + 1, scores[i]);
        }

        return team;
    }

    /**
     * Gets the schedule. Used in recursion for building
     * season schedules
     * @return Copy of the current schedule
     */
    public HashMap<Integer, String> getSchedule() {
       return new HashMap<>(currentSchedule);
    }

    public int getNumGames() {
        return currentSchedule.size();
    }

    public String getOpponent(int week) {
        return currentSchedule.get(week);
    }

    public FantasySchedule addGame(int week, String opponent) {
        currentSchedule.put(week, opponent);
        return this;
    }

}
