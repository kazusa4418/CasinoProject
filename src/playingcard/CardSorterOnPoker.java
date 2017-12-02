package playingcard;

public class CardSorterOnPoker extends CardSorter {
    public int compare(Card card1, Card card2) {
        //同じカードであるなら0を返す
        if (card1.equals(card2)) {
            return 0;
        }
        //自身のインスタンスがAならば1を返す
        if (card1.getNumber() == CardNumber.num1) {
            return 1;
        }
        //比較対象のインスタンスがAならば-1を返す
        if (card2.getNumber() == CardNumber.num1) {
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
        if (card1.getMark().ordinal() > card2.getMark().ordinal()) {
            return 1;
        } else {
            return -1;
        }
    }
}
