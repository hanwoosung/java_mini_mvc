package sports.items;

import sports.utils.FileUtils;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Closeable, FileUtils.ListParent {
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


    public void addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) {
            System.out.println("팀이 꽉찼습니다.");
            return;
        }
        players.add(player);
        System.out.println(player.getPlayerName() + " 선수 " + teamName + "  추가 ");
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

    @Override
    public String toString() {
        return teamName + "팀";
    }

    @Override
    public String getOutTagName() {
        return "팀";
    }

    @Override
    public String getName() {
        return teamName;
    }

    @Override
    public List<Object> getChild() {
        return new ArrayList<>(players);
    }
}
