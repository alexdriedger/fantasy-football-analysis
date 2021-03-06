package fourteam;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a fantasy football league
 */
public class FourTeamLeague {

    private Map<SnipperPlayers, FourTeamTeam> league;

    // FourTeamTeam records
    private static final double[] ALEX2015 = {135.34, 126.14, 140.82, 101.48, 104.80, 153.16, 169.12, 136.38, 144.82
            , 121.28, 103.36, 101.86, 133.78, 138.24};
    private static final double[] BILAL2015 = {127.00, 94.16, 139.00, 91.12, 147.58, 125.16, 163.92, 120.82, 141.86,
            133.36, 60.30, 104.02, 171.00, 174.50};
    private static final double[] RYAN2015 = {125.22, 95.82, 157.14, 137.62, 139.80, 153.64, 109.48, 153.18, 109.20,
            86.86, 123.80, 79.46, 150.88, 127.20};
    private static final double[] FRED2015 = {126.44, 115.74, 176.22, 114.62, 114.62, 121.50, 95.90, 111.74, 144.84,
            132.44, 102.80, 119.64, 104.08, 116.74};

    public FourTeamLeague() {
        this.league = new HashMap<>();
        this.league.put(SnipperPlayers.ALEX, new FourTeamTeam(SnipperPlayers.ALEX, 2015, ALEX2015));
        this.league.put(SnipperPlayers.BILAL, new FourTeamTeam(SnipperPlayers.BILAL, 2015, BILAL2015));
        this.league.put(SnipperPlayers.RYAN, new FourTeamTeam(SnipperPlayers.RYAN, 2015, RYAN2015));
        this.league.put(SnipperPlayers.FRED, new FourTeamTeam(SnipperPlayers.FRED, 2015, FRED2015));
    }

    public FourTeamTeam getTeam(SnipperPlayers name) {
        return league.get(name);
    }

    public Set<SnipperPlayers> getPlayers() {
        return league.keySet();
    }
}
