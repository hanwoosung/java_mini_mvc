package sports.utils;

import sports.items.Player;
import sports.items.Team;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUtils {
    public static void createFile(ArrayList<Player> players, ArrayList<Team> teams,String prefix) {

        String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String fileName = prefix + time + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write("전체 선수 리스트\n");
            for (Player p : players) {
                writer.write(p.toString() + "\n");
            }

            writer.write("전체 팀 목록 And 소속 선수\n");
            for (Team t : teams) {
                writer.write(t.getTeamName() + "팀명\n");

                for (Player p : t.getPlayers()) {
                    writer.write(p.toString() + "\n");
                }

                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
