package sports.view;

import interfaces_.MyLogger;
import sports.control.SportsController;

import java.util.Scanner;

public class MainView implements MyLogger {
    private Scanner scanner = new Scanner(System.in);
    private SportsController controller;

    public MainView(SportsController controller) {
        this.controller = controller;
    }

    public void start() {
        int input = 0;
        while (input != 99) {
            log("\n메뉴 선택:\n1. 선수 등록\n2. 선수 리스트 출력\n3. 선수를 팀에 넣기\n4. 팀 리스트 출력\n5. 팀 등록\n6. 팀 소속 선수 출력\n99. 종료");
            input = scanner.nextInt();
            scanner.nextLine();
            controller.receiveInput(input, this);
        }
    }

    public String getString(String message) {
        log(message, false);
        return scanner.nextLine();
    }

    public int getInt(String message) {
        log(message, false);
        return scanner.nextInt();
    }

    @Override
    public void log(String log) {
        log(log, true);
    }

    public void log(String log, boolean newLine) {
        System.out.print(log + (newLine ? "\n" : ""));
    }

    public void close() {
        scanner.close();
    }
}
