package util;

import data.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Save {
    private static Scanner sc = new Scanner(System.in);

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

    public static void deleteFile() {
        System.out.println("�f�B���N�g����ǂݍ��݂܂�\n");
        int length = showFiles();
        if (length == 0) return;
        System.out.print("�폜�������Z�[�u�t�@�C����I�����Ă�������\n> ");
        int index = Checker.numberCheck(sc.nextLine(), length);
        File file = getFile(index);
        file.delete();
        System.out.println("�Z�[�u�t�@�C�����폜���܂����B");
    }

    public static void deleteFile(int fileNumber) {
        File file = getFile(fileNumber);
        file.delete();
    }

    //�Z�[�u�f�[�^�����݂���ꍇ�͕Ԃ�l�Ƃ��ăZ�[�u�f�[�^����Ԃ�
    public static int showFiles() {
        File saveFolder = new File("saves");
        //�Z�[�u�f�[�^�����邩�ǂ����𔻕�
        if (!saveFolder.exists() || saveFolder.listFiles().length == 0) {
            System.out.println("�Z�[�u�f�[�^������܂���B\n");
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

    public static File getFile(int index) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();
        //index�͂����߂̃Z�[�u�f�[�^����\���Y���ł���
        //index��1?��\���̂ŁA�z��œY���Ƃ��Ďg���ꍇ��index - 1������
        return saveFiles[index - 1];
    }

    public static File getFile(String fileName) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();
        //fileName�͎擾�������Z�[�u�t�@�C���̃t�@�C�������w�肷��
        return matchSaveFile(fileName);
    }

    public static Data readSaveFile(File file, Data readData) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
            readData = (Data) ois.readObject();
            return readData;
        } catch (IOException e) {
            System.err.print("IOException : ");
            System.err.println("�Z�[�u�f�[�^�̓ǂݍ��݂Ɏ��s���܂����B\n�����I�����܂��B");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException : ");
            System.out.println("�N���X�̃L���X�g�Ɏ��s���܂����B\n�����I�����܂��B");
            System.exit(1);
        }
        return null;
    }

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
