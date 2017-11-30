package card;

import card.element.CardMark;
import card.element.CardNumber;
import card.sorter.CardSorter;

import java.util.ArrayList;
import java.util.Collections;

public class CardStock extends ArrayList<Card> {

    //引数がない場合はジョーカーを二枚含むものとしてスタックを生成する
    public CardStock() {
        this(2);
    }

    public CardStock(int joker_number) {
        //52枚分のカードの各インスタンスを作成し、リストに格納する
        CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < 13; i++) {
            this.add(new Card(numbers[i], CardMark.SPADE));
            this.add(new Card(numbers[i], CardMark.HEART));
            this.add(new Card(numbers[i], CardMark.DIAMOND));
            this.add(new Card(numbers[i], CardMark.CLOVER));
        }
        for (int i = 0; i < joker_number; i++) {
            this.add(new Card(CardNumber.JOKER, CardMark.JOKER));
        }
        sort();
    }

    //スタックからカードを一枚取得するメソッド
    public Card getCard() {
        Card card = this.get(0);
        this.remove(0);
        return card;
    }

    //スタックをシャッフルするメソッド
    public void shuffle() {
        Collections.shuffle(this);
    }

    //スタックをソートする->数の大きい順->マークの強い順
    private void sort() {
        Collections.sort(this, new CardSorter());
    }
}
