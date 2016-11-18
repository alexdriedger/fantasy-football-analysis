package analysis;

import schedule.League;
import schedule.Player;

import java.util.*;

import static javax.swing.UIManager.get;
import static sun.audio.AudioPlayer.player;

/**
 * Keeps track of the records of teams for a current season
 */
public class Season {

    private Map<Player, Integer> records;
    private League league;

    public Season() {
        records = new HashMap<>();
        records.put(Player.ALEX, 0);
        records.put(Player.RYAN, 0);
        records.put(Player.BILAL, 0);
        records.put(Player.FRED, 0);
        league = new League();
    }

    private void addWinner(Player winner) {
        int currentWins = records.get(winner);
        records.put(winner, currentWins + 1);
    }

    public void computeAndStoreWinner(Player team0, Player team1, int year, int week) {
        // Determine winner
        double result = league.getTeam(team0).getScore(year, week) - league.getTeam(team1).getScore(year, week);

        // Increment winner's record
        if (result > 0) {
            addWinner(team0);
        }
        else if (result < 0) {
            addWinner(team1);
        }
        else {
            System.out.println("There was a tie. You should deal with that");
        }
    }

    public List<Player> getFinalsMatchup(int year) {

        if (records.get(Player.RYAN) > 10) {
            System.out.println("YAYYYY");
        }

        List<Player> finals = new ArrayList<>();

        Player bestPlayer = getBestPlayer(records.keySet(), year);

//        System.out.println(bestPlayer);

        finals.add(getBestPlayer(records.keySet(), year));

        Set<Player> remainingPlayers = records.keySet();
        remainingPlayers.remove(bestPlayer);

//        Player nextBestPlayer = getBestPlayer(remainingPlayers, year);
//        System.out.println(nextBestPlayer);

        finals.add(getBestPlayer(remainingPlayers, year));

        return finals;

    }

    private Player getBestPlayer(Set<Player> players, int year) {
        int maxWins = 0;
        Player firstPlace = null;

        for (Player player : players) {
            int currentWins = records.get(player);
            if (currentWins > maxWins) {
                firstPlace = player;
                maxWins = currentWins;
            }
            else if (currentWins == maxWins) {
                if (league.getTeam(player).getPointsFor(year) > league.getTeam(firstPlace).getPointsFor(year)) {
                    firstPlace = player;
                }
            }
        }

        assert firstPlace != null;

        return firstPlace;
    }

    public void clearSeason() {
        records.clear();
        records.put(Player.ALEX, 0);
        records.put(Player.RYAN, 0);
        records.put(Player.BILAL, 0);
        records.put(Player.FRED, 0);
    }
}
