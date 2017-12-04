package casino;

import menu.Callback;
import menu.Menu;
import util.Checker;
import util.InputScanner;
import util.Printer;
import util.Save;

public class Main implements Callback {
    //入出力
    private InputScanner sc = new InputScanner();
    private Printer printer = new Printer();
    //メニューを管理する
    private Menu menu = new Menu();
    //プレイヤー
    private Player pl = Player.getInstance();
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
        while(true) {
            showMenu();
        }
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
                PlayData pd = createNewData();
                pl.setPlayData(pd);
                title.start();
                break;
            case 2:
                pd = readFile();
                pl.setPlayData(pd);
                title.start();
                break;
            case 3:
                deleteFile();
                break;
            case 4:
                printer.println("ゲームを終了します。");
                System.exit(0);
        }
    }

    private PlayData createNewData(){
        printer.println("新規セーブデータを作成します。");
        PlayData pd = new PlayData();
        System.out.print("プレイヤーネームを入力してください(半角英数字)\n> ");
        pd.setName(sc.scanLine("[0-9a-zA-Z]+"));
        printer.println("セーブデータの作成に成功しました。");
        return pd;
    }

    private PlayData readFile() {
        int size = Save.showFiles();
        System.out.print("どのセーブファイルを読み込みますか？(数字で入力)\n> ");
        int index = Checker.numberCheck(sc.scanLine(), size);
        return (PlayData)Save.readFile(Save.getFile(index));
    }

    private void deleteFile() {
        int size = Save.showFiles();
        System.out.println("どのセーブファイルを削除しますか？(数字で入力)\n> ");
        int index = Checker.numberCheck(sc.scanLine(), size);
        Save.getFile(index).delete();
    }
}
