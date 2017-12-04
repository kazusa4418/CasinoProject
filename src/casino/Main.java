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
    //readされたセーブファイルのナンバーを格納する
    public static int fileNumber = 0;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("ようこそ カジノへ\n");
        System.out.println("1:ゲームを始める\n2:セーブデータを作成する\n3:セーブデータを読み込む\n4:セーブデータを削除する\n5:終了する");
        System.out.println("------------------------------");
        System.out.print("(数字で入力)\n> ");
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
            System.out.println("終了します。");
            try {
                Thread.sleep(1500);
                System.exit(0);
            } catch (InterruptedException e) {
                System.out.println(e);
                System.out.println("例外が発生しました。\n強制終了します。");
                System.exit(0);
            }
        }
        //プレイヤーとコンピュータのインスタンスを作成
        Player pl = new Player(data);
        ComPlayer com = new ComPlayer();
        Title.title(pl, com);
    }

    public static void createNewSaveFile() {
        Scanner sc = new Scanner(System.in);
        File newFile = Save.createNewFile();
        //プレイヤーデータを作るのに必要な項目を入力させる
        System.out.print("プレイヤーネームを入力してください\n> ");
        String name = sc.nextLine();
        //入力された値を用いてインスタンスを作る
        PlayData newData = new PlayData(name);
        Save.writeFile(newFile, newData);
        printer.println("セーブファイルを作成しました。");

        ConsoleControl.clearScreen();
        menu();
    }

    private static PlayData readSaveFile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("セーブデータをロードします。\n");
        int size = Save.showFiles();
        //セーブデータがなかった時に、GUESTとしてインスタンスを返す
        if (size == 0) {
            System.out.println("GUESTとしてゲームを始めます");
            return new PlayData();
        }
        System.out.print("どのセーブデータをロードしますか？\n> ");
        int index = Checker.numberCheck(sc.nextInt(), size);
        File readFile = Save.getFile(index);
        fileNumber = index;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(readFile));) {
            PlayData readData = (PlayData) ois.readObject();
            printer.println("セーブデータのロードに成功しました。\n");

            ConsoleControl.clearScreen();

            return readData;
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("セーブデータのロードに失敗しました。\n強制終了します。");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            System.err.println("クラスのキャストに失敗しました。\n強制終了します。");
            System.exit(1);
        }
        return null;
    }
}
