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
 * Saveクラスはsavesフォルダの作成、savesフォルダ内のdatファイルの操作を実現します。
 * 実現できる操作はインスタンスの書き込み、読み込みといった動作で主にセーブファイルとしての
 * 機能を提供します。
 * savesフォルダはcreateNewFileメソッドが呼ばれた際に、作業パス上に作成されます。
 *
 * @author kazusa4418
 * @see File
 * @see FileInputStream
 * @see FileOutputStream
 * @see ObjectInputStream
 * @see ObjectOutputStream
 */
public class Save {
    //標準入力と標準出力をサポートする
    private static Printer printer = new Printer();
    private static Scanner sc = new Scanner(System.in);

    /**
     * 新しくsavesフォルダの中にdatファイルを作成します。
     * このメソッドが呼ばれると処理中にファイル名の入力を求められます。
     * 入力したファイル名は「save\ファイル名.dat」として処理されます。
     *
     * @throws IOException - 入出力エラーが発生したとき
     * @return 新規に作成されたdatファイルを返します。
     */
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

    /**
     * savesフォルダの中に存在するdatファイルの削除します。
     * ファイルは処理中にファイル一覧が実行され、それぞれの要素に対応する添字の入力を
     * 実行することで指定します。
     * savesフォルダの中に要素がひとつも存在しなかった場合はそのことを画面に出力し、
     * 処理を終了します。
     */
    public static void deleteFile() {
        System.out.println("ディレクトリを読み込みます\n");
        int length = showFiles();
        if (length == 0) return;
        System.out.print("削除したいセーブファイルを選択してください\n> ");
        int index = Checker.numberCheck(sc.nextLine(), length);
        File file = getFile(index);
        file.delete();
        printer.println("セーブファイルを削除しました。");
        ConsoleControl.clearScreen();
    }

    /**
     * savesフォルダの中に存在するdatファイルの削除をします。
     * int型変数でsavesフォルダの中に存在する要素を指定します。
     * savesフォルダに存在するファイルは作られた順に順序付けがされます。
     *
     * @param fileNumber - フォルダの要素を指定するint型変数
     */
    public static void deleteFile(int fileNumber) {
        File file = getFile(fileNumber);
        file.delete();
    }

    /**
     * savesフォルダの中に存在するdatファイルの要素を一覧します。
     * savesフォルダに存在するファイルは作られた順に順序付けされており、　
     * 1: ファイル名　のように　
     * indexと一緒にファイル名が出力されます。
     *
     * @return ファイルが存在した場合、その個数を返します。
     */
    public static int showFiles() {
        File saveFolder = new File("saves");
        //セーブデータがあるかどうかを判別
        if (!saveFolder.exists() || saveFolder.listFiles().length == 0) {
            printer.println("セーブデータがありません。\n");

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

    /**
     * savesフォルダの中にあるdatファイルを返します。
     * int型変数でsavesフォルダの中に存在するファイルを指定します。
     * savesフォルダに存在するファイルは作られた順に順序付けがされます。
     *
     * @param index - フォルダのファイルを指定するint型変数
     * @return ファイルが存在した場合そのファイルが返却されます。
     */
    public static File getFile(int index) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();
        //indexはいくつめのセーブデータかを表す添字である
        //indexは1?を表すので、配列で添字として使う場合はindex - 1を扱う
        return saveFiles[index - 1];
    }

    /**
     * savesフォルダの中にあるdatファイルを返します。
     * 引数で与えられたString型オブジェクトと拡張子つきファイル名が一致するものがあれば
     * それを返します。
     *
     * @param fileName - 文字列でファイル名を指定します。
     * @return
     */
    public static File getFile(String fileName) {
        File saveFolder = new File("saves");
        File[] saveFiles = saveFolder.listFiles();
        //fileNameは取得したいセーブファイルのファイル名を指定する
        return matchSaveFile(fileName);
    }

    /**
     * 引数で与えられたFileオブジェクトに記録されているオブジェクトデータを返却します。
     * 読み込まれたデータはData型オブジェクトとして返却されます。
     * このメソッドで読み込ませる場合はData型インターフェースを実装してください。
     *
     * @param file - 読み込むファイル
     * @throws IOException 入出力エラーが発生した場合
     * @throws ClassNotFoundException 指定された名前のクラス定義が見つからなかった場合
     * @return 読み込んだData型オブジェクトを返却します。
     */
    public static Data readFile(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
            Data readData = (Data) ois.readObject();
            return readData;
        } catch (IOException e) {
            System.err.print("IOException : ");
            System.err.println("セーブデータの読み込みに失敗しました。\n強制終了します。");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException : ");
            System.out.println("指定されたクラスが見つかりませんでした。\n強制終了します。");
            System.exit(1);
        }
        return null;
    }

    /**
     * ファイルにData型オブジェクトを書き込みます。
     * このメソッドを使用するには書き込むオブジェクトがData型インターフェースを実装している必要があります。
     *
     * @param file - 書き込みを行うファイルです。
     * @param writeData - ファイルに書き込むData型オブジェクトです。
     * @throws IOException 入出力エラーが発生した場合
     */
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

    /**
     * savesフォルダの中の要素に引数で与えられた文字列と一致するファイル名のものがあるか検索します。
     *
     * @param fileName - 検索するファイル名
     * @return 一致するファイルがあった場合そのファイルを返却します。
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
