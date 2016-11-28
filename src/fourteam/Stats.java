package fourteam;

import java.util.*;

import static javax.swing.UIManager.get;
import static fourteam.FantasySchedule.*;

/**
 * Analysis for fantasy schedules
 */
public class Stats {

    public static int[] getWinsDistribution(Set<FantasySchedule> schedules, int year, SnipperPlayers teamName) {
        FourTeamLeague snipper = new FourTeamLeague();

        // Find the team to get stats on
        FourTeamTeam currentTeam = snipper.getTeam(teamName);

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

    public static Map<SnipperPlayers, Integer> simSeason(Set<FantasySchedule> schedules, int year, SnipperPlayers currentPlayer) {
        FourTeamLeague snipper = new FourTeamLeague();
        Season season = new Season();
        List<SnipperPlayers> opponents = new ArrayList<>(Arrays.asList(SnipperPlayers.values()));
        opponents.remove(currentPlayer);
        List<SnipperPlayers> secondGame;

        Map<SnipperPlayers, Integer> champions = new HashMap<>();
        champions.put(SnipperPlayers.ALEX, 0);
        champions.put(SnipperPlayers.RYAN, 0);
        champions.put(SnipperPlayers.BILAL, 0);
        champions.put(SnipperPlayers.FRED, 0);

        for (FantasySchedule fantasySchedule : schedules) {

            // Simulate Regular Season
            for (int weekMinusOne = 0; weekMinusOne < NUM_GAMES_PER_SEASON; weekMinusOne++) {
                // Current opponent
                SnipperPlayers firstOpponent = fantasySchedule.getOpponent(weekMinusOne);

                // Compute winners of the one game
                season.computeAndStoreWinner(currentPlayer, firstOpponent, year, weekMinusOne);

                secondGame = new ArrayList<>(opponents);
                secondGame.remove(firstOpponent);

                season.computeAndStoreWinner(secondGame.get(0), secondGame.get(1),
                        year, weekMinusOne);
            }

            List<SnipperPlayers> finals = season.getFinalsMatchup(year);

            assert finals.size() == 2;

            // Compute winner more generally next time
            if (finals.contains(SnipperPlayers.BILAL)) {
                Integer currentRangs = champions.get(SnipperPlayers.BILAL);
                champions.put(SnipperPlayers.BILAL, currentRangs + 1);
            }
            else if (finals.contains(SnipperPlayers.FRED)) {
                Integer currentRangs = champions.get(SnipperPlayers.FRED);
                champions.put(SnipperPlayers.FRED, currentRangs + 1);
            }
            else if (finals.contains(SnipperPlayers.RYAN)) {
                Integer currentRangs = champions.get(SnipperPlayers.RYAN);
                champions.put(SnipperPlayers.RYAN, currentRangs + 1);
            }
            season.clearSeason();
            // Store answer in array
            // Clear season record
        }

        return champions;
    }
}
