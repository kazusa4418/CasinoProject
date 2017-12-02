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
                System.out.println("セーブファイルを保存するディレクトリの作成に失敗しました。");
                System.out.println("システムを強制終了します。");
                System.exit(1);
            }
        }
        boolean flag = true;
        String fileName = "";
        while (flag) {
            flag = false;
            System.out.print("セーブファイルを作成します。\nファイル名を決めてください\n(ローマ字か数字のみ使えます。)\n> ");
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
                System.out.println("そのファイル名は既に使われています。\n別のファイル名を入力してください。\n");
            }
        }

        fileName = "saves\\" + fileName + ".dat";
        File newFile = new File(fileName);
        try {
            newFile.createNewFile();
            return newFile;
        } catch (IOException e) {
            System.out.println(e);
            System.err.println("システムを強制終了します。");
            System.exit(1);
        }
        return null;
    }

    public static void deleteFile() {
        System.out.println("ディレクトリを読み込みます\n");
        int length = showFiles();
        if (length == 0) return;
        System.out.print("削除したいセーブファイルを選択してください\n> ");
        int index = Checker.numberCheck(sc.nextLine(), length);
        File file = getFile(index);
        file.delete();
        System.out.println("セーブファイルを削除しました。");
    }

    public static void deleteFile(int fileNumber) {
        File file = getFile(fileNumber);
        file.delete();
    }

    //セーブデータが存在する場合は返り値としてセーブデータ数を返す
    public static int showFiles() {
        File saveFolder = new File("saves");
        //セーブデータがあるかどうかを判別
        if (!saveFolder.exists() || saveFolder.listFiles().length == 0) {
            System.out.println("セーブデータがありません。\n");
            return 0;
        }
        File[] saveFiles = saveFolder.listFiles();
        List<String> fileNames = new ArrayList<>();
        for (File saveFile : saveFiles) {
            String name = saveFile.toString().replace("saves\\", "").replace(".dat", "");
            fileNames.add(name);
        }
        int i = 0;
        //フォルダーに存在する各セーブファイルのファイル名を画面に出力する
        System.out.println("******************************\n");
        System.out.println("セーブデータ一覧:");
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
        //indexはいくつめのセーブデータかを表す添字である
        //indexは1?を表すので、配列で添字として使う場合はindex - 1を扱う
        return saveFiles[index - 1];
    }

    public static File getFile(String fileName) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();
        //fileNameは取得したいセーブファイルのファイル名を指定する
        return matchSaveFile(fileName);
    }

    public static Data readSaveFile(File file, Data readData) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
            readData = (Data) ois.readObject();
            return readData;
        } catch (IOException e) {
            System.err.print("IOException : ");
            System.err.println("セーブデータの読み込みに失敗しました。\n強制終了します。");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException : ");
            System.out.println("クラスのキャストに失敗しました。\n強制終了します。");
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
            System.err.println("セーブデータの記録中にエラーが発生しました。");
            System.out.println("システムを強制終了します。");
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
