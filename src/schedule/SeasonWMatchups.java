package schedule;

import fourteam.Season;
import fourteam.FourTeamLeague;
import fourteam.SnipperPlayers;
import schedule.Week;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A Season for a general league with any amount of teams
 */
public class SeasonWMatchups {

    // Wins for each player
    private Map<SnipperPlayers, Integer> wins;
    // Lists of Weeks. Each Week contains Matchups
    private List<Week> wkSchedules;
    // FourTeamLeague the season takes place in
    private FourTeamLeague league;
    // Year for the league
    private int year;

    public SeasonWMatchups(Set<SnipperPlayers> players, FourTeamLeague league, int year) {
        // Add all players to the map of wins. All Players start with 0 wins
        this.wins = players.stream().collect(Collectors.toMap(Function.identity(), player -> 0));
        this.league = league;
        this.year = year;
    }

    // Return the league the season is in
    public FourTeamLeague getLeague() {
        return league;
    }

    // Creates all possible seasons for a given league
    public static List<Season> createSeasons(FourTeamLeague league) {
        // List of players in the league
        List<SnipperPlayers> players = new ArrayList<>(league.getPlayers());

        // create all possible matchups in a week



        // Do I only need to create one possible week and then iterate through it for each
        // week in the season?

        return new ArrayList<Season>(); // TODO: Change this
    }
}
