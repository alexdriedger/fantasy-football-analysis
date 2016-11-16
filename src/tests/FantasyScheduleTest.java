package tests;

import org.junit.Assert;
import org.junit.Test;
import schedule.FantasySchedule;

/**
 * Test for {@link schedule.FantasySchedule}
 */
public class FantasyScheduleTest {

    @Test
    public void arrayCopyDoubleTest() {
        double[] initial = {1.1,2.2,3.3};
        double[] result = new double[3];
        System.arraycopy(initial,0, result, 0, initial.length);

        result[2] = 14.2;
        result[0] = .4;

        double[] expected = new double[]{1.1, 2.2, 3.3};
        Assert.assertArrayEquals(expected, initial, .001);
    }

    @Test
    public void arrayCopyStringTest() {
        String[] initial = {"Bilal", "Fred", "Ryan"};
        String[] result = new String[3];
        System.arraycopy(initial,0, result, 0, initial.length);

        result[1] = "Alex";
        result[0] = "Alex";

        String[] expected = new String[]{"Bilal", "Fred", "Ryan"};;
        Assert.assertArrayEquals(expected, initial);
    }

    @Test
    public void createEmptySchedule() {
        String[] result = new String[3];

        for (int i = 0; i < result.length; i++) {
            result[i] = "";
        }

        for(String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void getNumGamesTest() {
        FantasySchedule fs = new FantasySchedule();

        Assert.assertEquals(0, fs.getNumGames());

        fs.addGame(0, "Bilal");

        Assert.assertEquals(1, fs.getNumGames());

        fs.addGame(5, "Fred");

        Assert.assertEquals(2, fs.getNumGames());
    }

}
