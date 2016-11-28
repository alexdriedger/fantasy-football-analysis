package fourteamtests;

import org.junit.Assert;
import org.junit.Test;
import fourteam.FantasySchedule;
import fourteam.SnipperPlayers;

import java.util.Set;

/**
 * Test for {@link FantasySchedule}
 */
public class FantasyScheduleTest {

    @Test
    public void getNumGamesTest() {
        FantasySchedule fs = new FantasySchedule();

        Assert.assertEquals(0, fs.getNumGames());

        fs.addGame(0, SnipperPlayers.BILAL);

        Assert.assertEquals(1, fs.getNumGames());

        fs.addGame(5, SnipperPlayers.FRED);

        Assert.assertEquals(2, fs.getNumGames());
    }

    @Test
    public void getWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(SnipperPlayers.ALEX);
        Assert.assertEquals(756756, schedules.size());
    }

}
