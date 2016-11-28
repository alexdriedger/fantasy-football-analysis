package tests;

import analysis.Stats;
import org.junit.Assert;
import org.junit.Test;
import schedule.FantasySchedule;
import schedule.Player;

import java.util.Set;

/**
 * Test for {@link schedule.FantasySchedule}
 */
public class FantasyScheduleTest {

    @Test
    public void getNumGamesTest() {
        FantasySchedule fs = new FantasySchedule();

        Assert.assertEquals(0, fs.getNumGames());

        fs.addGame(0, Player.BILAL);

        Assert.assertEquals(1, fs.getNumGames());

        fs.addGame(5, Player.FRED);

        Assert.assertEquals(2, fs.getNumGames());
    }

    @Test
    public void getWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(Player.ALEX);
        Assert.assertEquals(756756, schedules.size());
    }

}
