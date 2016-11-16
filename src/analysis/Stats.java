package analysis;

import schedule.FantasySchedule;
import schedule.League;
import schedule.Team;

import java.util.*;

import static schedule.FantasySchedule.*;

/**
 * Analysis for fantasy schedules
 */
public class Stats {

    public static int[] getWinsDistribution(Set<FantasySchedule> schedules, int year, String teamName) {
        League snipper = new League();

        // Find the team to get stats on
        Team currentTeam;
        switch (teamName) {
            case "Alex": currentTeam = snipper.getTeam("Alex");
                break;
            case "Ryan": currentTeam = snipper.getTeam("Ryan");
                break;
            case "Bilal": currentTeam = snipper.getTeam("Bilal");
                break;
            case "Fred": currentTeam = snipper.getTeam("Fred");
                break;
            default: throw new IllegalArgumentException(teamName + " is not in this league");
        }

        int[] teamWins = new int[NUM_GAMES_PER_SEASON + 1];

        for (FantasySchedule fantasySchedule : schedules) {
            // Find current wins for each schedule
            int seasonWins = 0;

            // Go through one week at a time
            for (int weekMinusOne = 0; weekMinusOne < NUM_GAMES_PER_SEASON; weekMinusOne++) {
//                String opponent : fantasySchedule.getSchedule()) {
                // Score for the current team
                double weekScore = currentTeam.getScore(year, weekMinusOne);
                double opponentScore = snipper.getTeam(fantasySchedule.getOpponent(weekMinusOne)).getScore(year, weekMinusOne);

                // If the current team won, increase season wins
                if (weekScore > opponentScore){
                    seasonWins++;
                }

            }
            teamWins[seasonWins] = teamWins[seasonWins] + 1;
        }

        return teamWins;
    }
}
