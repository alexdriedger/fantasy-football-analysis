package schedule;

import fourteam.FourTeamTeam;
import fourteam.SnipperPlayers;

/**
 * Matchup between two teams
 */
public class Matchup {

    // Week the matchup is occurring in
    private Week week;
    // FourTeamTeam names
    private SnipperPlayers team1Name;
    private SnipperPlayers team2Name;

    private FourTeamTeam team1;
    private FourTeamTeam team2;

    /**
     * Creates a Matchup with two teams
     * @param week the matchup is occurring in
     * @param team1
     * @param team2
     */
    public Matchup(Week week, SnipperPlayers team1, SnipperPlayers team2) {
        // FourTeamLeague the matchup is occurring in
        this.week = week;
        // Set team names
        this.team1Name = team1;
        this.team2Name = team2;

        // Gets the Teams from the FourTeamLeague
        this.team1 = week.getLeague().getTeam(team1);
        this.team2 = week.getLeague().getTeam(team2);
    }

    /**
     * Gets team1's Name
     * @return SnipperPlayers with team name
     */
    public SnipperPlayers getTeam1Name() {
        return team1Name;
    }

    /**
     * Gets team2's Name
     * @return SnipperPlayers with team name
     */
    public SnipperPlayers getTeam2Name() {
        return team2Name;
    }

    // Get the winner of the Matchup
    public SnipperPlayers getWinner() {
        int year = week.getSeason().getyear();
        int weekNumber = week.getWeekNumber();

        // Determine winner
        // Use weekNumber - 1 because getScore method is for the week - 1
        double result = team1.getScore(year, weekNumber - 1) - team2.getScore(year, weekNumber);

        // Increment winner's record
        if (result > 0) {
            return team1Name;
        }
        else if (result < 0) {
            return team2Name;
        }
        else {
            throw new IllegalStateException("There was a tie. You should deal with that");
        }
    }

    public boolean equals(Matchup other) {
        // If both teams are the same in both matchups, the matchup is equal
        // i.e. it doesn't matter who is home or away
        return ( team1Name.equals(other.getTeam1Name()) &&
                team2Name.equals(other.getTeam2Name()) ) ||
                ( team1Name.equals(other.getTeam2Name()) &&
                team2Name.equals(other.getTeam1Name()) );
    }
}
