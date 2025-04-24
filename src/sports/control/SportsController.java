package sports.control;

import sports.SportsInformation;
import sports.items.Player;
import sports.items.Team;
import sports.utils.FileUtils;
import sports.view.MainView;

import java.io.Closeable;

public class SportsController implements Closeable {
    private SportsInformation currentInformation;

    public SportsController(SportsInformation currentInformation) {
        this.currentInformation = currentInformation;
    }

    public void receiveInput(int input, MainView view) {
        switch (input) {
            case 1 -> {
                String name = view.getString("선수 이름: ");
                String position = view.getString("포지션: ");
                currentInformation.getPlayers().add(new Player(name, position));
                view.log("선수 등록 완료");
            }
            case 2 -> {
                if (currentInformation.getPlayers().isEmpty()) {
                    view.log("등록된 선수가 없습니다.");
                } else {
                    for (int i = 0; i < currentInformation.getPlayers().getCount(); i++) {
                        Player p = currentInformation.getPlayers().get(i);
                        view.log((i + 1) + ". 이름: " + p.getPlayerName() + ", 포지션: " + p.getPosition());
                    }
                }
            }
            case 3 -> {
                if (currentInformation.getPlayers().isEmpty() || currentInformation.getTeams().getCount() == 0) {
                    view.log("선수나 팀이 없습니다.");
                    return;
                }

                currentInformation.getTeams().print(view);
                int teamNum = view.getInt("팀 번호 선택: ") - 1;
                Team team = currentInformation.getTeams().get(teamNum);

                if (team == null) {
                    view.log("잘못된 팀 번호");
                    return;
                }

                for (int i = 0; i < currentInformation.getPlayers().getCount(); i++) {
                    Player p = currentInformation.getPlayers().get(i);
                    view.log((i + 1) + ". " + p.getPlayerName() + " (" + p.getPosition() + ")");
                }

                int playerNum = view.getInt("선수 번호 선택: ") - 1;

                if (playerNum >= 0 && playerNum < currentInformation.getPlayers().getCount()) {
                    team.addPlayer(currentInformation.getPlayers().get(playerNum));
                    view.log("선수 팀에 추가 완료");
                } else {
                    view.log("잘못된 선수 번호");
                }
            }

            case 4 -> currentInformation.getTeams().print(view);
            case 5 -> {
                String teamName = view.getString("팀 이름 입력: ");
                currentInformation.getTeams().add(new Team(teamName));
                view.log("팀 등록 완료");
            }
            case 6 -> {
                currentInformation.getTeams().print(view);
                int teamIndex = view.getInt("팀 번호 선택: ") - 1;
                Team team = currentInformation.getTeams().get(teamIndex);

                if (team != null) {
                    team.printPlayers();
                } else {
                    view.log("잘못된 팀 번호");
                }
            }
            case 99 -> {
                view.log("프로그램 종료");
                view.close();
                FileUtils.createFile(currentInformation.getPlayers().getAll(), currentInformation.getTeams().getAll(), "sports_data");
                currentInformation.dispose();
            }
            default -> view.log("잘못된 입력입니다.");
        }
    }

    @Override
    public void close() {

    }
}
