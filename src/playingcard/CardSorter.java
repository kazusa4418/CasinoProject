package playingcard;
import java.util.Comparator;

public class CardSorter implements Comparator<Card> {
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
        return Integer.compare(card1.getMark().ordinal(), card2.getMark().ordinal());

    }
}
