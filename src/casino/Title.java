package casino;

import menu.Callback;
import menu.Menu;

public class Title implements Callback {
    //�v���C���[�C���X�^���X
    private Player pl = Player.getInstance();
    private ComPlayer com = ComPlayer.getInstance();
    //���j���[
    private Menu menu = new Menu();

    public Title() {
        addMenu();
    }

    public void start() {
        System.out.println("�^�C�g�����\�b�h���R�[������܂����B");

    }

    private void addMenu() {
        menu.addMenu(1, "")
    }

    public void callback(int id) {
        //
    }
}
