package casino;

import playingcard.Card;
import playingcard.CardStock;
import playingcard.Hand;

import java.io.File;

public class Player {
    private static Player pl = new Player();

    private Hand hand = new Hand();
    private CardStock stock;

    private File file;
    private PlayData pd;

    private int betChips = 0;

    private Player() {
    }

    public static Player getInstance() {
        return pl;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void showHand() {
        for (Card card : hand) {
            System.out.print(card);
        }
    }

    public void showHand(String designation) {
        switch(designation) {
            case "WITH_INDEX":
                for (int i = 1; i <= hand.size(); i++) {
                    System.out.println(i + ": " + hand.get(i - 1));
                }
                break;
        }
    }

    public void setCardStock(CardStock stock) {
        this.stock = stock;
    }

    public long getChips() {
        return pd.getChips();
    }

    public void getChips(int chips) {
        pd.getChips(chips);
    }

    public int getBetChips() {
        return this.betChips;
    }

    public void setBetChips(int chips) {
        this.betChips = chips;
    }

    public void lostChips(int chips) {
        pd.getChips((chips * -1));
    }

    public PlayData getPlayData() {
        return this.pd;
    }

    public void setPlayData(PlayData pd) {
        this.pd = pd;
    }

    public File getSaveFile() {
        return this.file;
    }

    public void setSaveFile(File file) {
        this.file = file;
    }

    public void showPlayData() {
        System.out.println("プレイヤーネーム : " + this.pd.getName());
        System.out.println("所持チップ数 : " + this.pd.getChips());
        System.out.println("プレイ時間 : " + this.pd.getPlayTime());
    }

    public void handChenge() {
        //未定義
    }

    public void draw() {
        hand.add(stock.takeCard());
    }
}
