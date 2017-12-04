package casino;

import menu.Callback;
import menu.Menu;
import util.Checker;
import util.InputScanner;
import util.Printer;
import util.Save;

import java.io.File;

public class Main implements Callback {
    //���j���[
    private Menu menu = new Menu();
    //���o��
    private InputScanner sc = new InputScanner();
    private Printer printer = new Printer();
    //�v���C���[
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
        menu.addMenu(1, "�V�����Q�[�����n�߂�", this);
        menu.addMenu(2, "��������͂��߂�", this);
        menu.addMenu(3, "�Z�[�u�f�[�^���폜����", this);
        menu.addMenu(4, "�Q�[�����I������", this);
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
                if (deleteFile()) printer.println("�Z�[�u�f�[�^���폜���܂����B");
                else printer.println("�Z�[�u�f�[�^�̍폜�Ɏ��s���܂����B");
                break;
            case 4:
                printer.println("�Q�[�����I�����܂��B");
                System.exit(0);
                break;
        }
    }

    private void menuSelect() {
        System.out.println("�悤�����J�W�m��\n");
        menu.show();
        System.out.print("\n(�����œ���)\n> ");
        menu.select();
    }

    private PlayData createFile() {
        PlayData pd = new PlayData();
        printer.println("�V�K�Z�[�u�f�[�^���쐬���܂��B");
        System.out.print("�v���C���[�l�[�������肵�Ă�������(�p����)\n> ");
        String name = sc.scanLine("[0-9a-zA-Z]+");
        pd.setName(name);
        printer.println("�Z�[�u�f�[�^���쐬���܂����B");
        pl.setSaveFile(Save.createNewFile());
        return pd;
    }

    private PlayData readFile() {
        printer.println("�Z�[�u�f�[�^�����[�h���܂��B");
        int size = Save.showFiles();
        if (size == 0) return createFile();
        System.out.print("�ǂ̃Z�[�u�f�[�^�����[�h���܂����H\n> ");
        int input = Checker.numberCheck(sc.scanInt(), size);
        File file = Save.getFile(input);
        pl.setSaveFile(file);
        return (PlayData)Save.readFile(file);
    }

    private boolean deleteFile() {
        printer.println("�Z�[�u�f�[�^�����[�h���܂��B");
        int size = Save.showFiles();
        if (size == 0) return false;
        System.out.print("�ǂ̃Z�[�u�f�[�^���폜���܂����H\n> ");
        int input = Checker.numberCheck(sc.scanInt(), size);
        return Save.getFile(input).delete();
    }
}
