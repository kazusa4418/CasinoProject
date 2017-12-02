package casino;

import playingcard.*;
import java.util.ArrayList;
import java.util.List;

public class ComPlayer {
    private Hand hand = new Hand();

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    //手札を公開するメソッド
    public void showHand() {
        for (Card card : this.hand) {
            System.out.print(card);
        }
        System.out.println();
    }

    //ゲームによって手札の公開方法が変わる
    public void showHand(String gameMode) {
        switch (gameMode) {
            case "runBlackJack":
                System.out.println(this.hand.get(0) + "(BACK OF CARD)");
                break;
        }
    }

    public void handChengeOnPoker() {
        //入れ替える手札を格納するリスト
        List<Boolean> result = handCheckOnPoker(this.hand);

        //受け取ったリストを用いて、手札のカードを入れ替える
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i)) {
                hand.set(i, PokerManager.stock.getCard());
            }
        }
    }

    private List<Boolean> handCheckOnPoker(Hand hand) {
        List<Boolean> flag = new ArrayList<>();
        int jokerCounter = 0;
        int counter = 0;

        //手札にあるJOKERの枚数を数える
        for (Card aHand : hand) {
            if (aHand.getNumber() == CardNumber.JOKER) {
                jokerCounter++;
            }
        }
        /*手札の中身を確認して、役を作るのに足りないカードの枚数をカウントして
         *その結果をリストに格納する。
         *リストを使ってどの役を作りに行くか判別する
         */

        //この部分は実装が大変難しく、時間がかかりそうなため別途作成することにする
        //よって未実装とし、アップデートという形で実装することにする
        //DEBUG
        flag.add(false);
        flag.add(true);
        flag.add(true);
        flag.add(false);
        flag.add(false);

        return flag;
    }
}
