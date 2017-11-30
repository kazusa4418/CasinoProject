package player.hand;

import card.Card;
import card.sorter.Sorter;
import poker.PokerManager;

import java.util.ArrayList;

public class Hand extends ArrayList<Card> {
    public void sortCard(Sorter format) {
        this.sort(format);
    }

    //手札を山札に戻すときに使うメソッド
    public Card retCard(int idx) {
        Card card = this.get(idx);
        this.remove(card);
        PokerManager.stock.add(card);
        return card;
    }
}
