package playingcard;

import java.util.Comparator;

public abstract class Sorter implements Comparator<Card> {
    //�I�[�o�[���C�h�K�{
    public abstract int compare(Card card1, Card card2);
}
