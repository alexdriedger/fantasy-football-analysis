package analysis;

import schedule.FantasySchedule;
import schedule.Team;

import java.util.*;

import static schedule.FantasySchedule.*;

/**
 * Created by Alex Driedger on 2016-11-12.
 */
public class Stats {

    public static int[] getWinsDistribution(Set<FantasySchedule> schedules, String teamName) {
        Team alex = new Team("Alex", ALEX2015);
        Team ryan = new Team("Ryan", RYAN2015);
        Team bilal = new Team("Bilal", BILAL2015);
        Team fred = new Team("Fred", FRED2015);

        Team currentTeam;
        switch (teamName) {
            case "Alex": currentTeam = alex;
                break;
            case "Ryan": currentTeam = ryan;
                break;
            case "Bilal": currentTeam = bilal;
                break;
            case "Fred": currentTeam = fred;
                break;
            default: throw new IllegalArgumentException(teamName + " is not in this league");
        }

        List<String> opponentList = new ArrayList<>(Arrays.asList(ALL_OPPONENTS));
        opponentList.remove(teamName);
        Map<String, Team> modOpponents = new HashMap<>();

        for(String name : opponentList) {
            switch (name) {
                case "Alex": modOpponents.put(name, alex);
                    break;
                case "Ryan": modOpponents.put(name, ryan);
                    break;
                case "Bilal": modOpponents.put(name, bilal);
                    break;
                case "Fred": modOpponents.put(name, fred);
                    break;
                default: throw new IllegalArgumentException(teamName + " is not in this league");
            }
        }

        Map<String, Team> opponents = Collections.unmodifiableMap(modOpponents);

        int[] teamWins = new int[NUM_GAMES_PER_SEASON + 1];

        for (FantasySchedule fantasySchedule : schedules) {
            // Find current wins for each schedule
            int seasonWins = 0;
            Set<Integer> keys = fantasySchedule.getSchedule().keySet();

            // Go through one week at a time
            for (Integer week : keys) {
                String opponent = fantasySchedule.getOpponent(week);

                // Score for the current team
                double weekScore = currentTeam.getScore(week);

                // If the current team won, increase season wins
                if (weekScore > opponents.get(opponent).getScore(week)){
                    seasonWins++;
                }

            }
            teamWins[seasonWins] = teamWins[seasonWins] + 1;
        }

        return teamWins;
    }
}
