package casino;

import menu.Callback;
import menu.Menu;
import util.InputScanner;
import util.Printer;
import util.Save;

import java.util.Scanner;

import static casino.PlayDataSample.setInstance;

public class Main implements Callback {
    //入出力
    private InputScanner sc = new InputScanner();
    private Printer printer = new Printer();
    //メニューを管理する
    private Menu menu = new Menu();
    //プレイヤーデータ
    private PlayDataSample data = PlayDataSample.getInstance();
    //title
    private Title title = new Title();

    private Main() {
        addMenu();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        showMenu();
    }

    private void showMenu() {
        System.out.println("------------------------------");
        menu.show();
        System.out.println("------------------------------");
        menu.select();
    }

    private void addMenu() {
        menu.addMenu(1, "最初からゲームを始める", this);
        menu.addMenu(2, "続きからゲームを始める", this);
        menu.addMenu(3, "セーブデータを削除する", this);
        menu.addMenu(4, "ゲームを終了する", this);
    }

    public void callback(int no) {
        switch(no) {
            case 1:
                createNewData();
                title.start();
                break;
            case 2:
                break;
            case 3:
                readFile();
                break;
            case 4:
                printer.println("ゲームを終了します。");
                System.exit(0);
        }
    }
    private void readFile() {
        Save.showFiles();
        System.out.print("どのセーブファイルを読み込みますか？(数字で入力)\n> ");
        int index = sc.scanInt();
        PlayDataSample.setInstance((PlayDataSample)Save.readFile(Save.getFile(index)));
    }

    private void createNewData() {
        printer.println("新規セーブデータを作成します。");
        PlayDataSample newData = PlayDataSample.getInstance().setClear();
        System.out.print("プレイヤーネームを入力してください(半角英数字)\n> ");
        newData.setName(sc.scanLine("[0-9a-zA-Z]+"));
        printer.println("セーブデータの作成に成功しました。");
    }
}
