package sports;

import sports.implements_.Players;
import sports.implements_.Teams;

import java.io.Closeable;

public class SportsInformation implements Closeable {
    private Teams teamManager = new Teams(0);
    private Players playersManager = new Players(0);

    public Teams getTeams() {
        return teamManager;
    }

    public Players getPlayers() {
        return playersManager;
    }

    public void dispose() {
        teamManager.clear();
        playersManager.clear();
    }

    @Override
    public void close() {

    }
}
