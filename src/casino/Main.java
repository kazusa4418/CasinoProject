package casino;

import menu.Callback;
import menu.Menu;
import util.InputScanner;
import util.Printer;
import util.Save;

import java.util.Scanner;

import static casino.PlayDataSample.setInstance;

public class Main implements Callback {
    //���o��
    private InputScanner sc = new InputScanner();
    private Printer printer = new Printer();
    //���j���[���Ǘ�����
    private Menu menu = new Menu();
    //�v���C���[�f�[�^
    private PlayDataSample data = PlayDataSample.getInstance();
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
        showMenu();
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
                createNewData();
                title.start();
                break;
            case 2:
                break;
            case 3:
                readFile();
                break;
            case 4:
                printer.println("�Q�[�����I�����܂��B");
                System.exit(0);
        }
    }
    private void readFile() {
        Save.showFiles();
        System.out.print("�ǂ̃Z�[�u�t�@�C����ǂݍ��݂܂����H(�����œ���)\n> ");
        int index = sc.scanInt();
        PlayDataSample.setInstance((PlayDataSample)Save.readFile(Save.getFile(index)));
    }

    private void createNewData() {
        printer.println("�V�K�Z�[�u�f�[�^���쐬���܂��B");
        PlayDataSample newData = PlayDataSample.getInstance().setClear();
        System.out.print("�v���C���[�l�[������͂��Ă�������(���p�p����)\n> ");
        newData.setName(sc.scanLine("[0-9a-zA-Z]+"));
        printer.println("�Z�[�u�f�[�^�̍쐬�ɐ������܂����B");
    }
}
