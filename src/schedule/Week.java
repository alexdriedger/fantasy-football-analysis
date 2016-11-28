package schedule;

import analysis.Season;

import java.util.*;

/**
 * One week in a Fantasy Football Season
 */
public class Week {

    // Season that the league takes place in
    private Season season;
    // All the matchups of the week
    private Set<Matchup> matchups;
    // The week number in the season
    private int weekNum;

    public Week(Season season, int weekNumber) {
        this.season = season;
        this.weekNum = weekNumber;
        this.matchups = new HashSet<>();
    }

    public int getWeekNumber() {
        return weekNum;
    }

    public Season getSeason() {
        return season;
    }

    public League getLeague() {
        return season.getLeague();
    }

    public Set<Matchup> getMatchups() {
        return matchups;
    }

    public void addMatchup(Matchup game) {
        matchups.add(game);
    }

    public static List<Matchup> getAllPossibleMatchups(List<Player> players) {
        // Create copy of list so it can be modified without modifying the original list
        List<Player> currentPlayers = new ArrayList<>();
        Collections.copy(currentPlayers, players);

        List<Matchup> matchups = new ArrayList<>();

        for (Player cp : currentPlayers) {
            currentPlayers.remove(cp);
            for (Player other : currentPlayers) {
                // TODO: Add new Matchup to the list with (cp, other)
            }

        }

        return new ArrayList<Matchup>(); // TODO: Change this
    }
}
