package schedule;

import analysis.Stats;

import java.util.*;

import static com.intellij.dvcs.push.VcsPushReferenceStrategy.all;
import static com.intellij.openapi.vfs.ex.dummy.DummyFileIdGenerator.next;

enum Player{
    ALEX, BILAL, RYAN, FRED
}

/**
 * Fantasy Schedule for one team
 */
public class FantasySchedule {

    public static final String[] ALL_OPPONENTS = {"Alex", "Bilal", "Ryan", "Fred"};

    public static final int NUM_GAMES_PER_SEASON = 14;
    public static final int MAX_HEAD_TO_HEAD_GAMES = 5;
    public static final int START_OF_SEASON = 0;

    // Opponent for each week of the season is held in an array of Strings.
    // Opponent from the week 'i' is held in index 'i-1'. Eg. Week 1 opponent is located
    // at index 0, week 2 opponent is at index 1..... week 14 opponent is at index 13
    private String[] currentSchedule;

    public FantasySchedule() {
        currentSchedule = new String[NUM_GAMES_PER_SEASON];
        for (int i = 0; i < currentSchedule.length; i++) {
            currentSchedule[i] = "";
        }
    }

    public FantasySchedule(FantasySchedule current) {
        this.currentSchedule = current.getSchedule();
    }


    /**
     * Gets the schedule. Used in recursion for building
     * season schedules
     * @return Copy of the current schedule
     */
    public String[] getSchedule() {
        String[] newSchedule = new String[NUM_GAMES_PER_SEASON];
        System.arraycopy(currentSchedule, 0, newSchedule, 0, NUM_GAMES_PER_SEASON);
        return newSchedule;
    }

    public int getNumGames() {
        int count = 0;
        for (String game : currentSchedule) {
            if (!game.equals("")) {
                count++;
            }
        }
        return count;
    }

    public String getOpponent(int week) {
        return currentSchedule[week];
    }

    public FantasySchedule addGame(int week, String opponent) {
        currentSchedule[week] = opponent;
        return this;
    }

    public static Set<FantasySchedule> createSchedulesNonRecursive(String teamName) {

        // Creates list of opponents for a given team
        List<String> modOpponents = new ArrayList<>(Arrays.asList(ALL_OPPONENTS));
        modOpponents.remove(teamName);
        List<String> opponents = Collections.unmodifiableList(modOpponents);

        // Set for possible schedules
        final Set<FantasySchedule> finalSchedule = new HashSet<>();

        FantasySchedule initialSchedule = new FantasySchedule();

        Set<FantasySchedule> currentSchedules = new HashSet<>();
        currentSchedules.add(initialSchedule);

        for (int gamesPlayed = 0; gamesPlayed < NUM_GAMES_PER_SEASON; gamesPlayed++) {
            Set<FantasySchedule> nextSet = new HashSet<>();
            Iterator<FantasySchedule> it = currentSchedules.iterator();
            while (it.hasNext()) {
                FantasySchedule current = it.next();

                // Check amount of head to head games with each opponent
                int team0 = 0;
                int team1 = 0;
                int team2 = 0;

                String[] sched = current.getSchedule();
                for (int i = 0; i < gamesPlayed; i++) {
                    if ( sched[i].equals(opponents.get(0)) ) {
                        team0++;
                    } else if ( sched[i].equals(opponents.get(1)) ) {
                        team1++;
                    } else if ( sched[i].equals(opponents.get(2)) ) {
                        team2++;
                    }
                }

                if (team0 < MAX_HEAD_TO_HEAD_GAMES) {
                    nextSet.add(new FantasySchedule(current.addGame(gamesPlayed, opponents.get(0))));
                }
//                printStringArray(current.getSchedule());
//                System.out.println();
                if (team1 < MAX_HEAD_TO_HEAD_GAMES) {
                    nextSet.add(new FantasySchedule(current.addGame(gamesPlayed, opponents.get(1))));
                }
//                printStringArray(current.getSchedule());
//                System.out.println();
                if (team2 < MAX_HEAD_TO_HEAD_GAMES) {
                    nextSet.add(new FantasySchedule(current.addGame(gamesPlayed, opponents.get(2))));
                }
//                printStringArray(current.getSchedule());
//                System.out.println();
//                System.out.println(nextSet.size());
            }
            currentSchedules.clear();
            currentSchedules.addAll(nextSet);
            System.out.println(gamesPlayed);
        }

        return currentSchedules;
    }

    private static void  printStringArray(String[] schedule) {
        for (String s : schedule) {
            System.out.print(s + " ");
        }
    }

    public static Set<FantasySchedule> createSchedules(String teamName) {

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

        String[] sched = current.getSchedule();
        for (String currentOpponent : sched) {
            if ( currentOpponent.equals(opponents.get(0)) ) {
                team0++;
            } else if ( currentOpponent.equals(opponents.get(1)) ) {
                team1++;
            } else if ( currentOpponent.equals(opponents.get(2)) ) {
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
//            System.out.println(schedule.size());
        }
    }

    public static void main(String[] args) {

        System.out.println("This is the win distributions for the 2015 snipper season");
        try{Thread.sleep(2000);} catch (Exception e){}
        System.out.println("We will now simulate all 750 000 possible schedules");
        try{Thread.sleep(2000);} catch (Exception e){}
        System.out.println("Let's see how well people do....");
        try{Thread.sleep(2000);} catch (Exception e){}
        System.out.println("Creating all possible schedules");
        long t = System.currentTimeMillis();
        distributions("Alex", 2015);

        // Create all possible schedules
        int currentTime = (int) (System.currentTimeMillis() - t) / 1000;
        System.out.println("Total time to create schedules: " + currentTime);

        // Print results
        distributions("Ryan", 2015);
        distributions("Bilal", 2015);
        distributions("Fred", 2015);
    }

    private static void distributions(String teamName, int year) {
        Set<FantasySchedule> schedules = createSchedulesNonRecursive(teamName);
        int possibleSchedules = schedules.size();
        int[] wins = Stats.getWinsDistribution(schedules, year, teamName);
        printDistribution(teamName, wins, possibleSchedules);

//        Iterator it = schedules.iterator();
//        while (it.hasNext()) {
//            FantasySchedule fs = (FantasySchedule) it.next();
//            String[] s = fs.getSchedule();
//            for (String ss : s) {
//                System.out.print(ss + " ");
//            }
//            System.out.println();
//        }
        try {
            Thread.sleep(5000);
        } catch(Exception e){}
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

}
