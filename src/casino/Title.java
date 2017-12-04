package casino;

import menu.Callback;
import menu.Menu;

public class Title implements Callback {
    //メニュー
    private Menu menu = new Menu();
    //プレイヤー
    private Player pl = Player.getInstance();

    Title() {
        addMenu();
    }

    public void start() {
        do {
            System.out.println("------------------------------");
            menu.show();
            System.out.println("------------------------------");
            menu.select();
        } while (!SceneManager.callToMenu);
        SceneManager.callToMenu = false;
        

    }

    private void addMenu() {
        menu.addMenu(1, "ブラックジャック", this);
        menu.addMenu(2, "ポーカー", this);
        menu.addMenu(3, "プレイデータを参照", this);
        menu.addMenu(4, "メニューへ戻る", this);
    }

    public void callback(int id) {
        switch (id) {
            case 1:
                BlackJackManager bm = new BlackJackManager();
                bm.runGame();
            case 2:
                PokerManager pm = new PokerManager();
                pm.runGame();
            case 3:
                pl.showInfo();
            case 4:
                SceneManager.callToMenu = true;

        }
    }
}
