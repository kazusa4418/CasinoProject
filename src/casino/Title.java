package casino;

import menu.Callback;
import menu.Menu;
import util.Save;

public class Title implements Callback {
    //���j���[
    private Menu menu = new Menu();
    //�Q�[���}�l�[�W���[
    private Manager manager;
    //�v���C���[
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
        menu.addMenu(1, "�|�[�J�[�ŗV��", this);
        menu.addMenu(2, "�u���b�N�W���b�N�ŗV��", this);
        menu.addMenu(3, "�v���C�f�[�^���Q��", this);
        menu.addMenu(4, "�^�C�g�����j���[�֖߂�", this);
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
                //�N�\�R�[�h
                //���ƂŏC�������
                Save.writeFile(pl.getSaveFile(), pl.getPlayData());
                Main main = new Main();
                main.start();
        }
    }

    private void menuSelect() {
        menu.show();
        System.out.print("\n(�����œ���)\n> ");
        menu.select();
    }
}
