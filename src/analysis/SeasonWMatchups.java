package analysis;

import schedule.League;
import schedule.Player;
import schedule.Week;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A Season for a general league with any amount of teams
 */
public class SeasonWMatchups {

    // Wins for each player
    private Map<Player, Integer> wins;
    // Lists of Weeks. Each Week contains Matchups
    private List<Week> wkSchedules;
    // League the season takes place in
    private League league;
    // Year for the league
    private int year;

    public SeasonWMatchups(Set<Player> players, League league, int year) {
        // Add all players to the map of wins. All Players start with 0 wins
        this.wins = players.stream().collect(Collectors.toMap(Function.identity(), player -> 0));
        this.league = league;
        this.year = year;
    }

    // Return the league the season is in
    public League getLeague() {
        return league;
    }

    // Creates all possible seasons for a given league
    public static List<Season> createSeasons(League league) {
        // List of players in the league
        List<Player> players = new ArrayList<>(league.getPlayers());

        // create all possible matchups in a week



        // Do I only need to create one possible week and then iterate through it for each
        // week in the season?

        return new ArrayList<Season>(); // TODO: Change this
    }
}
