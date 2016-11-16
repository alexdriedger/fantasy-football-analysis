package schedule;

/**
 * Matchup between two teams
 */
public class Matchup {

    private static final String NEGATIVE_SCORE_MESSAGE = "Score cannot be negative";
    private static final String SCORE_NOT_SET_MESSAGE = "Score has not been set";

    // Team names
    private String team1Name;
    private String team2Name;

    // Team scores
    private double team1Score;
    private double team2Score;

    /**
     * Creates a Matchup with scores set to 0
     * @param team1
     * @param team2
     */
    public Matchup(String team1, String team2) {

        // Set team names
        this.team1Name = team1;
        this.team2Name = team2;

        // Set team scores
        this.team1Score = 0;
        this.team2Score = 0;
    }

    /**
     * Creates a Mathup with scores for both teams.
     * Throws IllegalArgumentException if scores are negative
     * @param team1
     * @param team2
     * @param team1Score
     * @param team2Score
     */
    public Matchup(String team1, String team2, double team1Score, double team2Score) {

        // Throw exception if either score is negative
        if (team1Score < 0 || team2Score < 0) {
            throw new IllegalArgumentException(NEGATIVE_SCORE_MESSAGE);
        }

        // Set team names
        this.team1Name = team1;
        this.team2Name = team2;

        // Set team scores
        this.team1Score = team1Score;
        this.team2Score = team2Score;
    }

    /**
     * Sets team 1 score
     * @param score
     */
    public void setTeam1Score(double score) {
        team1Score = score;
    }

    /**
     * Sets team 2 score
     * @param score
     */
    public void setTeam2Score(double score) {
        team2Score = score;
    }

    /**
     * Gets team1's Name
     * @return String with team name
     */
    public String getTeam1Name() {
        return team1Name;
    }

    /**
     * Gets team2's Name
     * @return String with team name
     */
    public String getTeam2Name() {
        return team2Name;
    }

    /**
     * Gets team1's Score
     * @return Team1's score
     */
    public double getTeam1Score() {
        return team1Score;
    }

    /**
     * Gets team2's Score
     * @return Team2's score
     */
    public double getTeam2Score() {
        return team2Score;
    }

    /**
     *
     * @return
     */
    public String getWinner() {
        if (team1Score == 0 && team2Score == 0) {
            throw new IllegalStateException(SCORE_NOT_SET_MESSAGE);
        }
        else if (team1Score > team2Score) {
            return team1Name;
        }
        else {
            return team2Name;
        }
    }
}
