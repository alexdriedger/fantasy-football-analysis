package schedule;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.get;
import static schedule.FantasySchedule.NUM_GAMES_PER_SEASON;

/**
 * Fantasy Football Team
 */
public class Team {

    private String teamName;
    private Map<Integer, Double> games;

    /**
     * Creates a team at the beginning of the season
     * @param name
     */
    public Team(String name) {
        this.teamName = name;
        this.games = new HashMap<>();
    }

    /**
     * Create a team with weekly scores
     * Precondition: scores.size() == NUM_GAMES_PER_SEASON
     * @param name of team
     * @param scores for the season
     */
    public Team(String name, double[] scores) {
        this.teamName = name;

        // Add a game for each week
        for (int i = 0; i < NUM_GAMES_PER_SEASON; i++) {
            this.addGame(i + 1, scores[i]);
        }
    }

    public void addGame(int week, double score) {
        games.put(week, score);
    }

    public double getScore(int week) {
        return games.get(week);
    }

    public String getTeamName() {
        return teamName;
    }

    public double getPointsFor() {
        double pointsFor = 0;

        // Sum scores from all weeks
        for (Integer week : games.keySet()) {
            pointsFor += games.get(week);
        }

        return pointsFor;
    }
}
