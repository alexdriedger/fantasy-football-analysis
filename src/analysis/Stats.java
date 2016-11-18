package analysis;

import com.btr.proxy.util.PlatformUtil;
import schedule.FantasySchedule;
import schedule.League;
import schedule.Player;
import schedule.Team;

import java.util.*;

import static javax.swing.UIManager.get;
import static schedule.FantasySchedule.*;

/**
 * Analysis for fantasy schedules
 */
public class Stats {

    public static int[] getWinsDistribution(Set<FantasySchedule> schedules, int year, Player teamName) {
        League snipper = new League();

        // Find the team to get stats on
        Team currentTeam = snipper.getTeam(teamName);

        int[] teamWins = new int[NUM_GAMES_PER_SEASON + 1];

        for (FantasySchedule fantasySchedule : schedules) {
            // Find current wins for each schedule
            int seasonWins = 0;

            // Go through one week at a time
            for (int weekMinusOne = 0; weekMinusOne < NUM_GAMES_PER_SEASON; weekMinusOne++) {
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

    public static Map<Player, Integer> simSeason(Set<FantasySchedule> schedules, int year, Player currentPlayer) {
        League snipper = new League();
        Season season = new Season();
        List<Player> opponents = new ArrayList<>(Arrays.asList(Player.values()));
        opponents.remove(currentPlayer);
        List<Player> secondGame;

        Map<Player, Integer> champions = new HashMap<>();
        champions.put(Player.ALEX, 0);
        champions.put(Player.RYAN, 0);
        champions.put(Player.BILAL, 0);
        champions.put(Player.FRED, 0);

        for (FantasySchedule fantasySchedule : schedules) {

            // Simulate Regular Season
            for (int weekMinusOne = 0; weekMinusOne < NUM_GAMES_PER_SEASON; weekMinusOne++) {
                // Current opponent
                Player firstOpponent = fantasySchedule.getOpponent(weekMinusOne);

                // Compute winners of the one game
                season.computeAndStoreWinner(currentPlayer, firstOpponent, year, weekMinusOne);

                secondGame = new ArrayList<>(opponents);
                secondGame.remove(firstOpponent);

                season.computeAndStoreWinner(secondGame.get(0), secondGame.get(1),
                        year, weekMinusOne);
            }

            List<Player> finals = season.getFinalsMatchup(year);

            assert finals.size() == 2;

            // Compute winner more generally next time
            if (finals.contains(Player.BILAL)) {
                Integer currentRangs = champions.get(Player.BILAL);
                champions.put(Player.BILAL, currentRangs + 1);
            }
            else if (finals.contains(Player.FRED)) {
                Integer currentRangs = champions.get(Player.FRED);
                champions.put(Player.FRED, currentRangs + 1);
            }
            else if (finals.contains(Player.RYAN)) {
                Integer currentRangs = champions.get(Player.RYAN);
                champions.put(Player.RYAN, currentRangs + 1);
            }
            season.clearSeason();
            // Store answer in array
            // Clear season record
        }

        return champions;
    }
}
