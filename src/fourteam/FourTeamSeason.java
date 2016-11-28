package fourteam;

import java.util.*;

import static javax.swing.UIManager.get;

/**
 * Keeps track of the records of teams for a current season
 */
public class FourTeamSeason {

    private Map<SnipperPlayers, Integer> records;
    private FourTeamLeague league;
    private int year;

    public FourTeamSeason() {
        records = new HashMap<>();
        records.put(SnipperPlayers.ALEX, 0);
        records.put(SnipperPlayers.RYAN, 0);
        records.put(SnipperPlayers.BILAL, 0);
        records.put(SnipperPlayers.FRED, 0);
        league = new FourTeamLeague();
        year = 2015; // TODO: Put this into contructor
    }

    // Return the league the season is in
    public FourTeamLeague getLeague() {
        return league;
    }

    public int getyear() {
        return year;
    }

    private void addWinner(SnipperPlayers winner) {
        int currentWins = records.get(winner);
        records.put(winner, currentWins + 1);
    }

    private SnipperPlayers computeWinner(SnipperPlayers team0, SnipperPlayers team1, int year, int week) {
        // Determine winner
        double result = league.getTeam(team0).getScore(year, week) - league.getTeam(team1).getScore(year, week);

        // Increment winner's record
        if (result > 0) {
            return team0;
        }
        else if (result < 0) {
            return team1;
        }
        else {
            throw new IllegalStateException("There was a tie. You should deal with that");
        }
    }

    public void computeAndStoreWinner(SnipperPlayers team0, SnipperPlayers team1, int year, int week) {
        addWinner(computeWinner(team0, team1, year, week));
    }

    public List<SnipperPlayers> getFinalsMatchup(int year) {

        if (records.get(SnipperPlayers.RYAN) > 10) {
            System.out.println("YAYYYY");
        }

        List<SnipperPlayers> finals = new ArrayList<>();

        SnipperPlayers bestPlayer = getBestPlayer(records.keySet(), year);

//        System.out.println(bestPlayer);

        finals.add(getBestPlayer(records.keySet(), year));

        Set<SnipperPlayers> remainingPlayers = records.keySet();
        remainingPlayers.remove(bestPlayer);

//        SnipperPlayers nextBestPlayer = getBestPlayer(remainingPlayers, year);
//        System.out.println(nextBestPlayer);

        finals.add(getBestPlayer(remainingPlayers, year));

        return finals;

    }

    private SnipperPlayers getBestPlayer(Set<SnipperPlayers> players, int year) {
        int maxWins = 0;
        SnipperPlayers firstPlace = null;

        for (SnipperPlayers player : players) {
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
        records.put(SnipperPlayers.ALEX, 0);
        records.put(SnipperPlayers.RYAN, 0);
        records.put(SnipperPlayers.BILAL, 0);
        records.put(SnipperPlayers.FRED, 0);
    }
}
