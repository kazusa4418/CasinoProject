package casino;

import menu.Callback;
import menu.Menu;
import util.Checker;
import util.InputScanner;
import util.Printer;
import util.Save;

public class Main implements Callback {
    //���o��
    private InputScanner sc = new InputScanner();
    private Printer printer = new Printer();
    //���j���[���Ǘ�����
    private Menu menu = new Menu();
    //�v���C���[
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
        menu.addMenu(1, "�ŏ�����Q�[�����n�߂�", this);
        menu.addMenu(2, "��������Q�[�����n�߂�", this);
        menu.addMenu(3, "�Z�[�u�f�[�^���폜����", this);
        menu.addMenu(4, "�Q�[�����I������", this);
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
                printer.println("�Q�[�����I�����܂��B");
                System.exit(0);
        }
    }

    private PlayData createNewData(){
        printer.println("�V�K�Z�[�u�f�[�^���쐬���܂��B");
        PlayData pd = new PlayData();
        System.out.print("�v���C���[�l�[������͂��Ă�������(���p�p����)\n> ");
        pd.setName(sc.scanLine("[0-9a-zA-Z]+"));
        printer.println("�Z�[�u�f�[�^�̍쐬�ɐ������܂����B");
        return pd;
    }

    private PlayData readFile() {
        int size = Save.showFiles();
        System.out.print("�ǂ̃Z�[�u�t�@�C����ǂݍ��݂܂����H(�����œ���)\n> ");
        int index = Checker.numberCheck(sc.scanLine(), size);
        return (PlayData)Save.readFile(Save.getFile(index));
    }

    private void deleteFile() {
        int size = Save.showFiles();
        System.out.println("�ǂ̃Z�[�u�t�@�C�����폜���܂����H(�����œ���)\n> ");
        int index = Checker.numberCheck(sc.scanLine(), size);
        Save.getFile(index).delete();
    }
}
