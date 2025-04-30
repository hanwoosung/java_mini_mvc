import sports.SportsInformation;
import sports.control.SportsController;
import sports.view.MainView;

public class Main {
    public static void main(String[] args) {
        try (
                SportsController controller = new SportsController(new SportsInformation())
        ) {
            MainView view = new MainView(controller);
            view.start();
        }
    }
}