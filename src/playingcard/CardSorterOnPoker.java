package playingcard;

import java.util.Comparator;

/**
 * CardSorterOnPokerはListなどでソートを行う際にソート方法を指定します。
 * ソート順序はポーカーのルールに従ってカードの強弱を比較しソートします。
 *
 * @author kazusa4418
 * @see Card
 * @see Comparator
 */
public class CardSorterOnPoker implements Comparator<Card> {
    /**
     * 受け取った2つのCard型インスタンスを比較します。
     *
     * @param card1 - 比較するCard型インスタンスです。
     * @param card2 - 比較されるCard型インスタンスです。
     * @return 比較するインスタンスが大小によって0, 1, -1 のいずれかが返却されます。
     */
    public int compare(Card card1, Card card2) {
        //同じカードであるなら0を返す
        if (card1.equals(card2)) {
            return 0;
        }
        //自身のインスタンスがJOKERならば1を返す
        if (card1.getNumber().ordinal() == 0) {
            return 1;
        }
        //比較対象のインスタンスがJOKERならば-1を返す
        if (card2.getNumber().ordinal() == 0) {
            return -1;
        }
        //自身のインスタンスがAならば1を返す
        if (card1.getNumber().ordinal() == 1) {
            return 1;
        }
        //比較対象のインスタンスがAならば-1を返す
        if (card2.getNumber().ordinal() == 1) {
            return -1;
        }
        //自身のインスタンスのほうが大きい数なら1を返す
        if (card1.getNumber().ordinal() > card2.getNumber().ordinal()) {
            return 1;
        }
        //比較対象のインスタンスのほうが大きい数なら-1を返す
        if (card1.getNumber().ordinal() < card2.getNumber().ordinal()) {
            return -1;
        }
        //カードの数字が同じなら、カードのマークで強さをはかる
        return Integer.compare(card1.getSuit().ordinal(), card2.getSuit().ordinal());
    }
}
