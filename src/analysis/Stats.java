package analysis;

import com.btr.proxy.util.PlatformUtil;
import schedule.FantasySchedule;
import schedule.League;
import schedule.Player;
import schedule.Team;

import java.util.*;

import static schedule.FantasySchedule.*;

/**
 * Analysis for fantasy schedules
 */
public class Stats {

    public static int[] getWinsDistribution(Set<FantasySchedule> schedules, int year, Player teamName) {
        League snipper = new League();

        // Find the team to get stats on
        Team currentTeam;
        switch (teamName) {
            case ALEX: currentTeam = snipper.getTeam(Player.ALEX);
                break;
            case RYAN: currentTeam = snipper.getTeam(Player.RYAN);
                break;
            case BILAL: currentTeam = snipper.getTeam(Player.BILAL);
                break;
            case FRED: currentTeam = snipper.getTeam(Player.FRED);
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
