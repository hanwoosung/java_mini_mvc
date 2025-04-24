package sports.items;

public class Player {
    private String playerName;
    private String position;

    public Player(String playerName, String position) {
        this.playerName = playerName;
        this.position = position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "playerName='" + playerName + '\'' +
                       ", position='" + position + '\'';
    }
}
