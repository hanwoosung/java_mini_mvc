import sports.SportsInformation;
import sports.control.SportsController;
import sports.view.MainView;

public class Main {
    public static void main(String[] args) {
        try (
                SportsInformation newSportsInformation = new SportsInformation();
                SportsController controller = new SportsController(newSportsInformation)
        ) {
            // 1.뷰와 컨트롤러 분리 (완료)
            // 2.팀 과 플레이어 파일로 추출 (완료)
            MainView view = new MainView(controller);
            view.start();
        }
    }
}