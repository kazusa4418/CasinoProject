package casino;

import menu.Callback;
import menu.Menu;
import util.Save;

public class Title implements Callback {
    //メニュー
    private Menu menu = new Menu();
    //ゲームマネージャー
    private Manager manager;
    //プレイヤー
    private Player pl = Player.getInstance();
    //flag
    private boolean flag = true;

    public Title() {
        addMenu();
    }

    public void start() {
        while(flag) {
            menuSelect();
        }
        manager.runGame();
    }

    private void addMenu() {
        menu.addMenu(1, "ポーカーで遊ぶ", this);
        menu.addMenu(2, "ブラックジャックで遊ぶ", this);
        menu.addMenu(3, "プレイデータを参照", this);
        menu.addMenu(4, "タイトルメニューへ戻る", this);
    }

    public void callback(int id) {
        switch(id) {
            case 1:
                manager = new PokerManager();
                flag = false;
                break;
            case 2:
                manager = new BlackJackManager();
                flag = false;
                break;
            case 3:
                pl.showPlayData();
                break;
            case 4:
                //クソコード
                //あとで修正しろや
                Save.writeFile(pl.getSaveFile(), pl.getPlayData());
                Main main = new Main();
                main.start();
        }
    }

    private void menuSelect() {
        menu.show();
        System.out.print("\n(数字で入力)\n> ");
        menu.select();
    }
}
