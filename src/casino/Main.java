package casino;

import util.Checker;
import util.ConsoleControl;
import util.Printer;
import util.Save;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {
    public static Printer printer = new Printer();
    //read���ꂽ�Z�[�u�t�@�C���̃i���o�[���i�[����
    public static int fileNumber = 0;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("�悤���� �J�W�m��\n");
        System.out.println("1:�Q�[�����n�߂�\n2:�Z�[�u�f�[�^���쐬����\n3:�Z�[�u�f�[�^��ǂݍ���\n4:�Z�[�u�f�[�^���폜����\n5:�I������");
        System.out.println("------------------------------");
        System.out.print("(�����œ���)\n> ");
        int command = Integer.parseInt(Checker.stringCheck(sc.nextLine(), "[12345]{1}"));

        ConsoleControl.clearScreen();

        PlayData data = new PlayData("");
        if (command == 1) {
            data = new PlayData();
        } else if (command == 2) {
            createNewSaveFile();
            menu();
            return;
        } else if (command == 3) {
            data = readSaveFile();
        } else if (command == 4) {
            Save.deleteFile();
            menu();
            return;
        } else {
            System.out.println("�I�����܂��B");
            try {
                Thread.sleep(1500);
                System.exit(0);
            } catch (InterruptedException e) {
                System.out.println(e);
                System.out.println("��O���������܂����B\n�����I�����܂��B");
                System.exit(0);
            }
        }
        //�v���C���[�ƃR���s���[�^�̃C���X�^���X���쐬
        Player pl = new Player(data);
        ComPlayer com = new ComPlayer();
        Title.title(pl, com);
    }

    public static void createNewSaveFile() {
        Scanner sc = new Scanner(System.in);
        File newFile = Save.createNewFile();
        //�v���C���[�f�[�^�����̂ɕK�v�ȍ��ڂ���͂�����
        System.out.print("�v���C���[�l�[������͂��Ă�������\n> ");
        String name = sc.nextLine();
        //���͂��ꂽ�l��p���ăC���X�^���X�����
        PlayData newData = new PlayData(name);
        Save.writeFile(newFile, newData);
        printer.println("�Z�[�u�t�@�C�����쐬���܂����B");

        ConsoleControl.clearScreen();
        menu();
    }

    private static PlayData readSaveFile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("�Z�[�u�f�[�^�����[�h���܂��B\n");
        int size = Save.showFiles();
        //�Z�[�u�f�[�^���Ȃ��������ɁAGUEST�Ƃ��ăC���X�^���X��Ԃ�
        if (size == 0) {
            System.out.println("GUEST�Ƃ��ăQ�[�����n�߂܂�");
            return new PlayData();
        }
        System.out.print("�ǂ̃Z�[�u�f�[�^�����[�h���܂����H\n> ");
        int index = Checker.numberCheck(sc.nextInt(), size);
        File readFile = Save.getFile(index);
        fileNumber = index;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(readFile));) {
            PlayData readData = (PlayData) ois.readObject();
            printer.println("�Z�[�u�f�[�^�̃��[�h�ɐ������܂����B\n");

            ConsoleControl.clearScreen();

            return readData;
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("�Z�[�u�f�[�^�̃��[�h�Ɏ��s���܂����B\n�����I�����܂��B");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            System.err.println("�N���X�̃L���X�g�Ɏ��s���܂����B\n�����I�����܂��B");
            System.exit(1);
        }
        return null;
    }
}
