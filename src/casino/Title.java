package casino;

import util.ConsoleControl;
import util.Save;
import util.Checker;
import java.io.File;
import java.util.Scanner;

public class Title {
    private static Scanner sc = new Scanner(System.in);

    public static void title(Player pl, ComPlayer com) {
        System.out.println("なんのゲームで遊びますか？\n");
        System.out.print("1:ブラックジャック\n2:ポーカー\n3:プレイデータを参照\n4:タイトルへ戻る\n> ");
        int command = Integer.parseInt(Checker.stringCheck(sc.nextLine(), "[1234]{1}"));
        if (command == 1) {
            BlackJackManager jack = new BlackJackManager(pl, com);
            jack.runGame();
            title(pl, com);
        } else if (command == 2) {
            PokerManager poker = new PokerManager(pl, com);
            poker.runGame();
            title(pl, com);
        } else if (command == 3) {
            pl.showPlayData();
            title(pl, com);
        } else {
            //タイトルへ戻る前にセーブ処理を行う
            if (pl.getPlayData().isGUEST()) {
                //GUESTであればセーブを行わずインスタンスを破棄する
                pl = null;
                System.out.println("タイトルへ戻ります。");

                ConsoleControl.sleep(2000);
                ConsoleControl.clearScreen();
                Main.menu();
            }
            System.out.println("セーブしてタイトルへ戻ります。");
            File file = Save.getFile(Main.fileNumber);
            PlayData myData = pl.getPlayData();
            Save.writeFile(file, myData);

            ConsoleControl.sleep(2000);
            ConsoleControl.clearScreen();

            Main.menu();
        }
    }
}
