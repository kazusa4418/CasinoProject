package playingcard;

import java.util.Comparator;

public abstract class Sorter implements Comparator<Card> {
    //オーバーライド必須
    public abstract int compare(Card card1, Card card2);
}
