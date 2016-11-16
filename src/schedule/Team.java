package schedule;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.get;
import static schedule.FantasySchedule.NUM_GAMES_PER_SEASON;

/**
 * Fantasy Football Team
 */
public class Team {

    private String teamName;

    // Integer represents the year the fantasy season begun in. i.e. the
    // year the first game took place from the given season.
    //
    // Score for each week of the season is held in an array of doubles.
    // Score from the week 'i' is held in index 'i-1'. Eg. Week 1 score is located
    // at index 0, week 2 score is at index 1..... week 14 score is at index 13
    private Map<Integer, double[]> seasons;

    /**
     * Creates a team at the beginning of the season
     * @param name
     */
    public Team(String name) {
        this.teamName = name;

        // Initialize all games with a score of 0
        this.seasons = new HashMap<>();
    }

    /**
     * Create a team with weekly scores
     * Precondition: scores.size() == NUM_GAMES_PER_SEASON
     * @param name of team
     * @param scores for the season
     */
    public Team(String name, int year, double[] scores) {
        this.teamName = name;

        // Initialize all games with a score of 0
        this.seasons = new HashMap<>();
        addSeason(year, scores);
    }

    public void addSeason(int year, double[] scores) {

        // Create a new array defensively
        double[] games = new double[NUM_GAMES_PER_SEASON];

        // Add a game for each week
        for (int i = 0; i < NUM_GAMES_PER_SEASON; i++) {
            games[i] = scores[i];
        }

        seasons.put(year, games);
    }

    public double getScore(int year, int week) {
        return seasons.get(year)[week];
    }

    public String getTeamName() {
        return teamName;
    }

    public double getPointsFor(int year) {
        double pointsFor = 0;

        // Sum scores from all weeks
        for (double week : seasons.get(year)) {
            pointsFor += week;
        }

        return pointsFor;
    }
}
