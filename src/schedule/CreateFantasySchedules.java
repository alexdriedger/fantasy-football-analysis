package schedule;

import com.intellij.util.containers.ConcurrentList;
import fourteam.FantasySchedule;
import fourteam.SnipperPlayers;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Create {@link FantasySchedule} in parallel
 */
public abstract class CreateFantasySchedules extends RecursiveAction {
    // Current incomplete schedule to build off of
    private SeasonWMatchups currentSchedule;

    // Use a concurrent list instead of a concurrent set because
    // there shouldn't be any duplicate entries, so we don't need
    // to check for duplicates, as a set would. This should hopefully
    // increase performance as the number of elements contained, increases.
    private ConcurrentList<SeasonWMatchups> finalSchedules;

    //
    private List<SnipperPlayers> opponents;
    // Size of current schedule
    private int currentGames;
    // Number of games in the regular season
    private int seasonLength;

    public CreateFantasySchedules(SeasonWMatchups currentSchedule, ConcurrentList<SeasonWMatchups> finalSchedules,
                                  List<SnipperPlayers> opponents, int currentGames, int seasonLength) {
        this.currentSchedule = currentSchedule;
        this.finalSchedules = finalSchedules;
        this.opponents = opponents;
        this.currentGames = currentGames;
        this.seasonLength = seasonLength;
    }

    // TODO: Update FourTeamSeason class to use Matchups instead of Fantasy Schedule
    // TODO: Implement this class
}
