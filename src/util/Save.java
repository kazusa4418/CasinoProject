package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Save�N���X��saves�t�H���_�̍쐬�Asaves�t�H���_����dat�t�@�C���̑�����������܂��B
 * �����ł��鑀��̓C���X�^���X�̏������݁A�ǂݍ��݂Ƃ���������Ŏ�ɃZ�[�u�t�@�C���Ƃ��Ă�
 * �@�\��񋟂��܂��B
 * saves�t�H���_��createNewFile���\�b�h���Ă΂ꂽ�ۂɁA��ƃp�X��ɍ쐬����܂��B
 *
 * @author kazusa4418
 * @see File
 * @see FileInputStream
 * @see FileOutputStream
 * @see ObjectInputStream
 * @see ObjectOutputStream
 */
public class Save {
    //�W�����͂ƕW���o�͂��T�|�[�g����
    private static Printer printer = new Printer();
    private static Scanner sc = new Scanner(System.in);

    /**
     * �V����saves�t�H���_�̒���dat�t�@�C�����쐬���܂��B
     * ���̃��\�b�h���Ă΂��Ə������Ƀt�@�C�����̓��͂����߂��܂��B
     * ���͂����t�@�C�����́usave\�t�@�C����.dat�v�Ƃ��ď�������܂��B
     *
     * @throws IOException - ���o�̓G���[�����������Ƃ�
     * @return �V�K�ɍ쐬���ꂽdat�t�@�C����Ԃ��܂��B
     */
    public static File createNewFile() {
        File saveFolder = new File("saves");
        if (!saveFolder.exists()) {
            if (!saveFolder.mkdir()) {
                System.out.println("�Z�[�u�t�@�C����ۑ�����f�B���N�g���̍쐬�Ɏ��s���܂����B");
                System.out.println("�V�X�e���������I�����܂��B");
                System.exit(1);
            }
        }
        boolean flag = true;
        String fileName = "";
        while (flag) {
            flag = false;
            System.out.print("�Z�[�u�t�@�C�����쐬���܂��B\n�t�@�C���������߂Ă�������\n(���[�}���������̂ݎg���܂��B)\n> ");
            fileName = Checker.stringCheck(sc.nextLine(), "[a-zA-Z0-9]+");

            File[] arrayFiles = saveFolder.listFiles();
            for (File arrayFile : arrayFiles) {
                String file = arrayFile.toString();
                int index = file.lastIndexOf("\\") + 1;
                String name = file.replace(".dat", "").substring(index);
                if (fileName.equals(name)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            } else {
                System.out.println("���̃t�@�C�����͊��Ɏg���Ă��܂��B\n�ʂ̃t�@�C��������͂��Ă��������B\n");
            }
        }

        fileName = "saves\\" + fileName + ".dat";
        File newFile = new File(fileName);
        try {
            newFile.createNewFile();
            return newFile;
        } catch (IOException e) {
            System.out.println(e);
            System.err.println("�V�X�e���������I�����܂��B");
            System.exit(1);
        }
        return null;
    }

    /**
     * saves�t�H���_�̒��ɑ��݂���dat�t�@�C���̍폜���܂��B
     * �t�@�C���͏������Ƀt�@�C���ꗗ�����s����A���ꂼ��̗v�f�ɑΉ�����Y���̓��͂�
     * ���s���邱�ƂŎw�肵�܂��B
     * saves�t�H���_�̒��ɗv�f���ЂƂ����݂��Ȃ������ꍇ�͂��̂��Ƃ���ʂɏo�͂��A
     * �������I�����܂��B
     */
    public static void deleteFile() {
        System.out.println("�f�B���N�g����ǂݍ��݂܂�\n");
        int length = showFiles();
        if (length == 0) return;
        System.out.print("�폜�������Z�[�u�t�@�C����I�����Ă�������\n> ");
        int index = Checker.numberCheck(sc.nextLine(), length);
        File file = getFile(index);
        file.delete();
        printer.println("�Z�[�u�t�@�C�����폜���܂����B");
        ConsoleControl.clearScreen();
    }

    /**
     * saves�t�H���_�̒��ɑ��݂���dat�t�@�C���̍폜�����܂��B
     * int�^�ϐ���saves�t�H���_�̒��ɑ��݂���v�f���w�肵�܂��B
     * saves�t�H���_�ɑ��݂���t�@�C���͍��ꂽ���ɏ����t��������܂��B
     *
     * @param fileNumber - �t�H���_�̗v�f���w�肷��int�^�ϐ�
     */
    public static void deleteFile(int fileNumber) {
        File file = getFile(fileNumber);
        file.delete();
    }

    /**
     * saves�t�H���_�̒��ɑ��݂���dat�t�@�C���̗v�f���ꗗ���܂��B
     * saves�t�H���_�ɑ��݂���t�@�C���͍��ꂽ���ɏ����t������Ă���A�@
     * 1: �t�@�C�����@�̂悤�Ɂ@
     * index�ƈꏏ�Ƀt�@�C�������o�͂���܂��B
     *
     * @return �t�@�C�������݂����ꍇ�A���̌���Ԃ��܂��B
     */
    public static int showFiles() {
        File saveFolder = new File("saves");
        //�Z�[�u�f�[�^�����邩�ǂ����𔻕�
        if (!saveFolder.exists() || saveFolder.listFiles().length == 0) {
            printer.println("�Z�[�u�f�[�^������܂���B\n");

            ConsoleControl.clearScreen();
            return 0;
        }
        File[] saveFiles = saveFolder.listFiles();
        List<String> fileNames = new ArrayList<>();
        for (File saveFile : saveFiles) {
            String name = saveFile.toString().replace("saves\\", "").replace(".dat", "");
            fileNames.add(name);
        }
        int i = 0;
        //�t�H���_�[�ɑ��݂���e�Z�[�u�t�@�C���̃t�@�C��������ʂɏo�͂���
        System.out.println("******************************\n");
        System.out.println("�Z�[�u�f�[�^�ꗗ:");
        while (i < saveFiles.length) {
            System.out.println((i + 1) + " : " + fileNames.get(i));
            i++;
        }
        System.out.println("******************************\n");
        return i;
    }

    /**
     * saves�t�H���_�̒��ɂ���dat�t�@�C����Ԃ��܂��B
     * int�^�ϐ���saves�t�H���_�̒��ɑ��݂���t�@�C�����w�肵�܂��B
     * saves�t�H���_�ɑ��݂���t�@�C���͍��ꂽ���ɏ����t��������܂��B
     *
     * @param index - �t�H���_�̃t�@�C�����w�肷��int�^�ϐ�
     * @return �t�@�C�������݂����ꍇ���̃t�@�C�����ԋp����܂��B
     */
    public static File getFile(int index) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();
        //index�͂����߂̃Z�[�u�f�[�^����\���Y���ł���
        //index��1?��\���̂ŁA�z��œY���Ƃ��Ďg���ꍇ��index - 1������
        return saveFiles[index - 1];
    }

    /**
     * saves�t�H���_�̒��ɂ���dat�t�@�C����Ԃ��܂��B
     * �����ŗ^����ꂽString�^�I�u�W�F�N�g�Ɗg���q���t�@�C��������v������̂������
     * �����Ԃ��܂��B
     *
     * @param fileName - ������Ńt�@�C�������w�肵�܂��B
     * @return
     */
    public static File getFile(String fileName) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();
        //fileName�͎擾�������Z�[�u�t�@�C���̃t�@�C�������w�肷��
        return matchSaveFile(fileName);
    }

    /**
     * �����ŗ^����ꂽFile�I�u�W�F�N�g�ɋL�^����Ă���I�u�W�F�N�g�f�[�^��ԋp���܂��B
     * �ǂݍ��܂ꂽ�f�[�^��Data�^�I�u�W�F�N�g�Ƃ��ĕԋp����܂��B
     * ���̃��\�b�h�œǂݍ��܂���ꍇ��Data�^�C���^�[�t�F�[�X���������Ă��������B
     *
     * @param file - �ǂݍ��ރt�@�C��
     * @throws IOException ���o�̓G���[�����������ꍇ
     * @throws ClassNotFoundException �w�肳�ꂽ���O�̃N���X��`��������Ȃ������ꍇ
     * @return �ǂݍ���Data�^�I�u�W�F�N�g��ԋp���܂��B
     */
    public static Data readFile(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
            Data readData = (Data) ois.readObject();
            return readData;
        } catch (IOException e) {
            System.err.print("IOException : ");
            System.err.println("�Z�[�u�f�[�^�̓ǂݍ��݂Ɏ��s���܂����B\n�����I�����܂��B");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException : ");
            System.out.println("�w�肳�ꂽ�N���X��������܂���ł����B\n�����I�����܂��B");
            System.exit(1);
        }
        return null;
    }

    /**
     * �t�@�C����Data�^�I�u�W�F�N�g���������݂܂��B
     * ���̃��\�b�h���g�p����ɂ͏������ރI�u�W�F�N�g��Data�^�C���^�[�t�F�[�X���������Ă���K�v������܂��B
     *
     * @param file - �������݂��s���t�@�C���ł��B
     * @param writeData - �t�@�C���ɏ�������Data�^�I�u�W�F�N�g�ł��B
     * @throws IOException ���o�̓G���[�����������ꍇ
     */
    public static void writeFile(File file, Data writeData) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));) {
            oos.writeObject(writeData);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("�Z�[�u�f�[�^�̋L�^���ɃG���[���������܂����B");
            System.out.println("�V�X�e���������I�����܂��B");
            System.exit(1);
        }
    }

    /**
     * saves�t�H���_�̒��̗v�f�Ɉ����ŗ^����ꂽ������ƈ�v����t�@�C�����̂��̂����邩�������܂��B
     *
     * @param fileName - ��������t�@�C����
     * @return ��v����t�@�C�����������ꍇ���̃t�@�C����ԋp���܂��B
     */
    public static File matchSaveFile(String fileName) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();

        for (File file : saveFiles) {
            if (fileName.equals(file.toString())) {
                return file;
            }
        }
        return null;
    }
}
