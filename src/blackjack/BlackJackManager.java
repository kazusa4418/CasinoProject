package blackjack;

import card.CardStock;
import card.element.CardNumber;
import card.sorter.CardSorter;
import player.ComPlayer;
import player.Player;
import player.hand.Hand;
import system.tools.InputScanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BlackJackManager {
    private static Scanner sc = new Scanner(System.in);
    private static Map<CardNumber, Integer> numbers = new HashMap<>();
    public static CardStock stock = new CardStock(0);
    public static Player pl;
    public static ComPlayer com;

    public BlackJackManager() {
        //マップにCardNumberとカードの数字を関連付ける
        for (CardNumber element : CardNumber.values()) {
            if (element == CardNumber.JOKER) {
                continue;
            }
            if (element == CardNumber.num1) {
                numbers.put(element, 1);
                continue;
            }
            if (element.ordinal() < 9) {
                numbers.put(element, element.ordinal() + 2);
            } else {
                numbers.put(element, 10);
            }
        }
    }

    public BlackJackManager(Player pl, ComPlayer com) {
        this();
        this.pl = pl;
        this.com = com;
    }

    public void runGame() {
        System.out.println("DEBUG: ブラックジャックが実行されました");
        //各プレイヤーの手札を用いて役の強さを調べる
        int plStrong = BlackJack.handCheck(pl.getHand());
        int comStrong = BlackJack.handCheck(com.getHand());
        //プレイヤーの手札を公開する
        writeLine();
        System.out.println("あなたの手札:");
        pl.showHand();
        System.out.println("あなたの強さ: " + plStrong + "\n\n");
        //ディーラー(コンピュータ)の手札を公開する
        System.out.println("ディーラーの手札:");
        com.showHand("runBlackJack");
        writeLine();
        //勝負するまで行う行動を選択させる
        runAction();
        //いざ、勝負
    }

    public static void runAction() {
        //行う行動を選択させる
        String action = act(BlackJack.canSplit(pl.getHand()), BlackJack.canInsurance(com.getHand()));
        //actionに格納されている文字列から行う処理を決める
        switch (action) {
            case "HIT":
                pl.runHit();
                runAction();
                return;
            case "STAND":
                System.out.println("いざ、勝負！");
                return;
            case "DOUBLE":
                System.out.println("DEBUG:掛け金を二倍にします");
                runAction();
                return;
            case "SURRENDER":
                System.out.println("DEBUG:勝負を降りました");
                runAction();
                return;
            case "SPLIT":
                System.out.println("DEBUG:SPLITが実行されました");
                runAction();
                return;
            case "INSURANCE":
                System.out.println("DEBUG:INSURANCEが実行されました");
                runAction();
                return;
            case "NULL":
                System.err.println("エラー:強制終了");
                System.exit(1);
        }
    }

    public static Map<CardNumber, Integer> getNumberMap() {
        return numbers;
    }

    public static String act(boolean canSplit, boolean canInsurance) {
        List<String> action = new ArrayList<>();
        action.add(":HIT (カードを一枚引く)");
        action.add(":STAND (勝負する)");
        action.add(":DOUBLE (掛け金を二倍にしてカードを一枚引く)");
        action.add(":SURRENDER (掛け金を半分捨てて勝負を降りる)");

        if (canSplit) {
            action.add(":SPLITする");
        }
        if (canInsurance) {
            action.add(":INSURANCEする");
        }

        System.out.println("行動を選んでください\n");
        for (int i = 0; i < action.size(); i++) {
            System.out.println((i + 1) + action.get(i));
        }
        //リストのサイズから入力を受け付ける文字列を決定する
        String text = "";
        for (int i = 0; i < action.size(); i++) {
            text += (i + 1);
        }
        String format = "[" + text + "]{1}";
        InputScanner scanner = new InputScanner(format);

        System.out.println("\n(数字を入力して行動を選択)");
        System.out.print("> ");
        int command = scanner.scanInt();

        switch (command) {
            case 1:
                return "HIT";
            case 2:
                return "STAND";
            case 3:
                return "DOUBLE";
            case 4:
                return "SURRENDER";
            case 5:
                if (canSplit) {
                    return "SPLIT";
                }
                return "INSURANCE";
            case 6:
                return "INSURANCE";
        }
        System.out.println("行動がマッチングを逃しました");
        return "NULL";
    }

    public static void writeLine() {
        System.out.println("******************************************");
    }

    //初期手札を配るために用いられるstaticメソッド
    public static Hand distribute(Hand hand) {
        stock.shuffle();
        hand.add(stock.getCard());
        hand.add(stock.getCard());
        hand.sortCard(new CardSorter());
        return hand;
    }
}
