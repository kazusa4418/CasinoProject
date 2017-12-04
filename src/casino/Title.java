package casino;

import menu.Callback;
import menu.Menu;

public class Title implements Callback {
    //プレイヤーインスタンス
    private Player pl = Player.getInstance();
    private ComPlayer com = ComPlayer.getInstance();
    //メニュー
    private Menu menu = new Menu();

    public Title() {
        addMenu();
    }

    public void start() {
        System.out.println("タイトルメソッドがコールされました。");

    }

    private void addMenu() {
        menu.addMenu(1, "")
    }

    public void callback(int id) {
        //
    }
}
