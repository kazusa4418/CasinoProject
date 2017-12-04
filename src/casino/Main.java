package casino;

import menu.Callback;
import menu.Menu;
import util.Checker;
import util.InputScanner;
import util.Printer;
import util.Save;

import java.io.File;

public class Main implements Callback {
    //メニュー
    private Menu menu = new Menu();
    //入出力
    private InputScanner sc = new InputScanner();
    private Printer printer = new Printer();
    //プレイヤー
    private Player pl = Player.getInstance();
    //flag
    private boolean flag = true;

    public Main() {
        addMenu();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        while(flag) {
            menuSelect();
        }
        Title title = new Title();
        title.start();
    }

    private void addMenu() {
        menu.addMenu(1, "新しくゲームを始める", this);
        menu.addMenu(2, "続きからはじめる", this);
        menu.addMenu(3, "セーブデータを削除する", this);
        menu.addMenu(4, "ゲームを終了する", this);
    }

    public void callback(int id) {
        switch(id) {
            case 1:
                PlayData newPd = createFile();
                pl.setPlayData(newPd);
                flag = false;
                break;
            case 2:
                PlayData readPd = readFile();
                pl.setPlayData(readPd);
                flag = false;
                break;
            case 3:
                if (deleteFile()) printer.println("セーブデータを削除しました。");
                else printer.println("セーブデータの削除に失敗しました。");
                break;
            case 4:
                printer.println("ゲームを終了します。");
                System.exit(0);
                break;
        }
    }

    private void menuSelect() {
        System.out.println("ようこそカジノへ\n");
        menu.show();
        System.out.print("\n(数字で入力)\n> ");
        menu.select();
    }

    private PlayData createFile() {
        PlayData pd = new PlayData();
        printer.println("新規セーブデータを作成します。");
        System.out.print("プレイヤーネームを決定してください(英数字)\n> ");
        String name = sc.scanLine("[0-9a-zA-Z]+");
        pd.setName(name);
        printer.println("セーブデータを作成しました。");
        pl.setSaveFile(Save.createNewFile());
        return pd;
    }

    private PlayData readFile() {
        printer.println("セーブデータをロードします。");
        int size = Save.showFiles();
        if (size == 0) return createFile();
        System.out.print("どのセーブデータをロードしますか？\n> ");
        int input = Checker.numberCheck(sc.scanInt(), size);
        File file = Save.getFile(input);
        pl.setSaveFile(file);
        return (PlayData)Save.readFile(file);
    }

    private boolean deleteFile() {
        printer.println("セーブデータをロードします。");
        int size = Save.showFiles();
        if (size == 0) return false;
        System.out.print("どのセーブデータを削除しますか？\n> ");
        int input = Checker.numberCheck(sc.scanInt(), size);
        return Save.getFile(input).delete();
    }
}
