package playingcard;
import java.util.Comparator;

/**
 * CardSorterクラスはListなどでソートを行う際にソート方法を指定します。
 * ソート順序はトランプカードにおける自然順序です。
 *
 * @author kazusa4418
 * @see Card
 * @see Comparator
 */
public class CardSorter implements Comparator<Card> {
    /**
     * 順序付けのために2つのCard型オブジェクトを比較します。
     *
     * @param card1 - 比較するCard型オブジェクトです。
     * @param card2 - 比較されるCard型オブジェクトです。
     * @return 最初の引数が 2 番目の引数より小さい場合は負の整数、
     *         両方が等しい場合は 0、最初の引数が 2 番目の引数より大きい場合は正の整数。
     */
    public int compare(Card card1, Card card2) {
        //同じカードであれば0を返す
        if (card1.equals(card2)) {
            return 0;
        }
        //自身のインスタンスのほうが大きい数なら1を返す
        if (card1.getNumber().ordinal() > card2.getNumber().ordinal()) {
            return 1;
        }
        //比較対象のインスタンスのほうが大きい数なら-1を返す
        if (card1.getNumber().ordinal() < card2.getNumber().ordinal()) {
            return -1;
        }
        //数字が同じだったらマークを使って比較する
        return Integer.compare(card1.getSuit().ordinal(), card2.getSuit().ordinal());

    }
}
