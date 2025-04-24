package sports.items;

import java.io.Closeable;
import java.util.ArrayList;

public class Team implements Closeable {
    private static final int MAX_PLAYERS = 11;
    private String teamName;
    private ArrayList<Player> players = new ArrayList<>();

    @Override
    public void close() {
        players.clear();
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) {
            System.out.println("팀이 꽉찼습니다.");
            return false;
        }
        players.add(player);
        System.out.println(player.getPlayerName() + " 선수 " + teamName + "  추가 ");
        return true;
    }

    public void printPlayers() {
        System.out.println("====== " + teamName + " 팀 선수 목록 ======");
        if (players.isEmpty()) {
            System.out.println("소속된 선수 없슴다");
        } else {
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                System.out.println((i + 1) + " 이름: " + p.getPlayerName() + "\t포지션: " + p.getPosition());
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return teamName + "팀";
    }
}
