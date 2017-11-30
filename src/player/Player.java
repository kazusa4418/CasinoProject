package player;

import card.Card;
import blackjack.BlackJackManager;
import player.data.PlayData;
import player.hand.Hand;
import poker.PokerManager;
import system.tools.InputScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Player {
    //プレイヤーネームや所有チップ数など保存してあるクラス
    private PlayData myData;
    //手札
    private Hand hand = new Hand();
    //SPLITしたときのみ使われる
    private Hand splitHand;

    public Player(PlayData myData) {
        //セーブデータの読み込み
        this.myData = myData;
    }

    public void showPlayData() {
        System.out.println("******************************\n");
        System.out.println("プレイヤーネーム: " + this.myData.getName());
        System.out.println("所持チップ数: " + this.myData.getChips());
        System.out.println("プレイ時間: " + this.myData.getPlayTime());
        System.out.println("\n******************************\n");
    }

    public Hand getHand() {
        return this.hand;
    }

    public void showHand() {
        for (Card card : this.hand) {
            System.out.print(card);
        }
        System.out.println();
    }

    public PlayData getPlayData() {
        return this.myData;
    }

    public long getChips() {
        return this.myData.getChips();
    }

    public void getChips(int number) {
        this.myData.plusChips(number);
    }

    public void lostChips(int number) {
        this.myData.plusChips(number * -1);
    }

    public int betChips(int number) {
        return this.myData.getChips(number);
    }

    //HIT : カードを一枚引く
    public void runHit() {
        hand.add(BlackJackManager.stock.getCard());
    }

    //DOUBLE : 掛け金を二倍にしてカードを一枚引く
    public void runDouble() {
        //掛け金を二倍にする操作を挿入する
        this.runHit();
    }

    //SURRENDER : 掛け金を半分捨てて勝負を降りる
    public void runSurrender() {
        //掛け金を半分にする操作を挿入する
        System.out.println("掛け金を半分払って勝負を降ります。");
    }

    //SPLIT : 手札の2枚のカードが同じ数字の時、手札を2つに分割する
    public void runSplit() {
        splitHand.add(hand.get(1));
        hand.remove(1);
    }

    //INSURANCE : ディーラーの手札にAが含まれたいた時BJに保険をかける
    public void runInsurance() {
        Scanner sc = new Scanner(System.in);
        System.out.print("INSURANCEするチップの枚数を入力してください\n> ");
        int tips = sc.nextInt();
    }

    //ポーカーで手札を入れ替えるときに呼ばれるメソッド
    public void handChengeOnPoker() {
        //専用に入力を受け付けるクラスInputScannerを作った
        InputScanner scanner = new InputScanner("[12345]{1}|[OK]{2}|[ok]{2}|[Ok]{2}|[oK]{2}");
        //入れ替えるカードの添字を格納するリスト
        List<Integer> number = new ArrayList<>();

        System.out.print("入れ替えるカード番号を入力してください(一枚づつ番号を入力 : 終了はOKを入力)\n> ");
        String tmp = scanner.scanLine();

        while (!tmp.equalsIgnoreCase("ok")) {
            number.add(Integer.parseInt(tmp));
            System.out.print("> ");
            tmp = scanner.scanLine();
        }
        System.out.println();

        //重複された値の削除
        number = number.stream().distinct().collect(Collectors.toList());
        //ここに存在しない添え字を排除する処理を挿入する

        //入力された値から手札のカードを入れ替える
        for (int i : number) {
            Card card = this.hand.get(i - 1);
            this.hand.set(i - 1, PokerManager.getStock().getCard());
            PokerManager.stock.add(card);
        }
        PokerManager.stock.shuffle();
    }
}
