package schedule;

import fourteam.FourTeamLeague;
import fourteam.FourTeamSeason;
import fourteam.SnipperPlayers;

import java.util.*;

/**
 * One week in a Fantasy Football FourTeamSeason
 */
public class Week {

    // FourTeamSeason that the league takes place in
    private FourTeamSeason season;
    // All the matchups of the week
    private Set<Matchup> matchups;
    // The week number in the season
    private int weekNum;

    public Week(FourTeamSeason season, int weekNumber) {
        this.season = season;
        this.weekNum = weekNumber;
        this.matchups = new HashSet<>();
    }

    public int getWeekNumber() {
        return weekNum;
    }

    public FourTeamSeason getSeason() {
        return season;
    }

    public FourTeamLeague getLeague() {
        return season.getLeague();
    }

    public Set<Matchup> getMatchups() {
        return matchups;
    }

    public void addMatchup(Matchup game) {
        matchups.add(game);
    }

    public static List<Matchup> getAllPossibleMatchups(List<SnipperPlayers> players) {
        // Create copy of list so it can be modified without modifying the original list
        List<SnipperPlayers> currentPlayers = new ArrayList<>();
        Collections.copy(currentPlayers, players);

        List<Matchup> matchups = new ArrayList<>();

        for (SnipperPlayers cp : currentPlayers) {
            currentPlayers.remove(cp);
            for (SnipperPlayers other : currentPlayers) {
                // TODO: Add new Matchup to the list with (cp, other)
            }

        }

        return new ArrayList<Matchup>(); // TODO: Change this
    }
}
