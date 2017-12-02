package playingcard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * CardStockクラスはCardクラスのオブジェクトを複数保持して山札を実現します。
 *
 * @author kazusa4418
 * @see Card
 * @see ArrayList
 */
public class CardStock extends ArrayList<Card> {

    /**
     * JOKERを除くトランプで使われるカードすべてをCard型オブジェクトとして作成しListに保持します。
     * トランプの山札を作る動作を実現します。
     * デフォルトではJOKERは2枚入った状態で作成されます。
     * 初期のカードの並びは数の小さい順,マークの弱い順でソートされています。
     *
     * @author kazusa4418
     * @see Card
     * @see ArrayList
     */
    public CardStock() {
        this(2);
    }

    /**
     * JOKERを除くトランプで使われるカードすべてをCard型オブジェクトとして作成しListに保持します。
     * トランプの山札を作る動作を実現します。
     * 引数には山札にいれるJOKERの枚数を指定します。
     * 引数に負の数が渡された場合にはJOKERが0枚の状態でインスタンス化します。
     *
     * @param joker_number - 山札にいれるJOKERの枚数をint型変数で指定する
     */
    public CardStock(int joker_number) {
        //52枚分のカードの各インスタンスを作成し、リストに格納する
        CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < 13; i++) {
            this.add(new Card(numbers[i], CardSuit.SPADE));
            this.add(new Card(numbers[i], CardSuit.HEART));
            this.add(new Card(numbers[i], CardSuit.DIAMOND));
            this.add(new Card(numbers[i], CardSuit.CLOVER));
        }
        for (int i = 0; i < joker_number; i++) {
            this.add(new Card(CardNumber.JOKER, CardSuit.JOKER));
        }
    }


    /**
     * リストの0番目のCard型オブジェクトを返します。
     * 山札から一枚カードを引く動作を実現します。
     * CardStockオブジェクトに要素が存在しなかった場合例外がスローされます。
     *
     * @return Card型オブジェクトを返します。
     * @throws ArrayIndexOutOfBoundsException 要素が存在しない場合にtakeCardメソッドが呼ばれた場合
     */
    public Card takeCard() {
        Card card = this.get(0);
        this.remove(0);
        return card;
    }

    /**
     * リストの要素をランダムに入れ替えます。
     * 山札をシャッフルする動作を実現します。
     */
    public void shuffle() {
        Collections.shuffle(this);
    }
}
