package title;

import blackjack.BlackJackManager;
import player.ComPlayer;
import player.Player;
import player.data.PlayData;
import poker.PokerManager;
import system.checker.Checker;
import system.tools.Save;

import java.io.File;
import java.util.Scanner;

public class Title {
    private static Scanner sc = new Scanner(System.in);

    public static void start(Player pl, ComPlayer com) {
        System.out.println("なんのゲームで遊びますか？\n");
        System.out.print("1:ブラックジャック\n2:ポーカー\n3:プレイデータを参照\n4:タイトルへ戻る\n> ");
        int command = Integer.parseInt(Checker.stringCheck(sc.nextLine(), "[1234]{1}"));
        if (command == 1) {
            BlackJackManager jack = new BlackJackManager(pl, com);
            jack.runGame();
            start(pl, com);
        } else if (command == 2) {
            PokerManager poker = new PokerManager(pl, com);
            poker.runGame();
            start(pl, com);
        } else if (command == 3) {
            pl.showPlayData();
            start(pl, com);
        } else {
            //タイトルへ戻る前にセーブ処理を行う
            System.out.println("セーブしてタイトルへ戻ります。\n");
            File file = Save.getFile(Main.fileNumber);
            PlayData myData = pl.getPlayData();
            Save.writeFile(file, myData);
            Main.menu();
        }
    }
}
