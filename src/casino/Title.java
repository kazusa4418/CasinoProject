package casino;

import menu.Callback;
import menu.Menu;

public class Title implements Callback {
    //���j���[
    private Menu menu = new Menu();
    //�v���C���[
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
        menu.addMenu(1, "�u���b�N�W���b�N", this);
        menu.addMenu(2, "�|�[�J�[", this);
        menu.addMenu(3, "�v���C�f�[�^���Q��", this);
        menu.addMenu(4, "���j���[�֖߂�", this);
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
