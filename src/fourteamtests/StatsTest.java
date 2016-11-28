package fourteamtests;

import fourteam.Stats;
import org.junit.Assert;
import org.junit.Test;
import fourteam.FantasySchedule;
import fourteam.SnipperPlayers;

import java.util.Map;
import java.util.Set;

/**
 * Test for {@link FantasySchedule}
 */
public class StatsTest {

    @Test
    public void getAlexWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(SnipperPlayers.ALEX);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, SnipperPlayers.ALEX);
        int[] expected = new int[]{0, 0, 0, 480, 5919, 29695, 86392, 159586, 194152,
                157948, 85690, 29998, 6271, 625, 0};
        // Test the win distribution for Alex
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getRyanWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(SnipperPlayers.RYAN);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, SnipperPlayers.RYAN);
        int[] expected = new int[]{0,0, 0, 0, 9114, 65436, 178752, 250152, 178752, 65436,
                9114, 0, 0, 0, 0};

        // Test the win distribution for Ryan
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getBilalWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(SnipperPlayers.BILAL);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, SnipperPlayers.BILAL);
        int[] expected = new int[]{0, 0, 0, 0, 7350, 60732, 179760, 259140, 183204, 59640,
                6930, 0, 0, 0, 0};

        // Test the win distribution for Bilal
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void getFredWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(SnipperPlayers.FRED);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, SnipperPlayers.FRED);
        int[] expected = new int[]{0, 0, 0, 10920, 66150, 177660, 247296, 177660, 66150, 10920,
                0, 0, 0, 0, 0};

        // Test the win distribution for Fred
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void simSeasonTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(SnipperPlayers.BILAL);
        Map<SnipperPlayers, Integer> acutal = Stats.simSeason(schedules, 2015, SnipperPlayers.BILAL);

        // Test the championship counts
        Assert.assertEquals(0, (int) acutal.get(SnipperPlayers.ALEX));
        Assert.assertEquals(224537, (int) acutal.get(SnipperPlayers.RYAN));
        Assert.assertEquals(452182, (int) acutal.get(SnipperPlayers.BILAL));
        Assert.assertEquals(80037, (int) acutal.get(SnipperPlayers.FRED));
    }
}
