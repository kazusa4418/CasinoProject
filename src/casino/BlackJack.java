package casino;

import playingcard.*;

class BlackJack {
    //スプリット可能かどうか判定を行う
    static boolean canSplit(Hand hand) {
        return hand.get(0).getNumber() == hand.get(1).getNumber();
    }
    //インシュランス可能かどうか判定を行う
    static boolean canInsurance(Hand hand) {
        return hand.get(0).getNumber() == CardNumber.num1;
    }

    static int handCheck(Hand hand) {
        int sum = 0;
        hand.sort(new CardSorter());
        for (Card card : hand) {
            sum += BlackJackManager.getNumberMap().get(card.getNumber());
        }
        if (hand.get(0).getNumber() == CardNumber.num1 && sum < 12) {
            sum += 10;
        }

        return sum;
    }
}
