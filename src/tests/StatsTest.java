package tests;

import analysis.Stats;
import org.junit.Assert;
import org.junit.Test;
import schedule.FantasySchedule;
import schedule.Player;

import java.util.Set;

/**
 * Test for {@link FantasySchedule}
 */
public class StatsTest {

    @Test
    public void getAlexWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(Player.ALEX);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, Player.ALEX);
        int[] expected = new int[]{0, 0, 0, 480, 5919, 29695, 86392, 159586, 194152,
                157948, 85690, 29998, 6271, 625, 0};
        // Test the win distribution for Alex
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getRyanWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(Player.RYAN);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, Player.RYAN);
        int[] expected = new int[]{0,0, 0, 0, 9114, 65436, 178752, 250152, 178752, 65436,
                9114, 0, 0, 0, 0};

        // Test the win distribution for Ryan
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getBilalWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(Player.BILAL);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, Player.BILAL);
        int[] expected = new int[]{0, 0, 0, 0, 7350, 60732, 179760, 259140, 183204, 59640,
                6930, 0, 0, 0, 0};

        // Test the win distribution for Ryan
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void getFredWinsDistributionsTest() {
        Set<FantasySchedule> schedules = FantasySchedule.createSchedulesNonRecursive(Player.FRED);
        int[] actual = Stats.getWinsDistribution(schedules, 2015, Player.FRED);
        int[] expected = new int[]{0, 0, 0, 10920, 66150, 177660, 247296, 177660, 66150, 10920,
                0, 0, 0, 0, 0};

        // Test the win distribution for Ryan
        Assert.assertArrayEquals(expected, actual);
    }


}
