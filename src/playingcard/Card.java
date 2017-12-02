package playingcard;

public class Card {
    private final CardNumber number;
    private final CardMark mark;

    public Card(CardNumber number, CardMark mark) {
        this.number = number;
        this.mark = mark;
    }

    public CardNumber getNumber() {
        return this.number;
    }

    public CardMark getMark() {
        return this.mark;
    }

    public boolean equals(Card card) {
        if (!(card.number == this.number)) {
            return false;
        }
        if (!(card.mark == this.mark)) {
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.mark == CardMark.JOKER) {
            return "(" + this.mark + ")";
        }
        return "(" + this.number + "," + this.mark + ")";
    }

    public String toString(boolean flag) {
        //true���󂯎��ƃJ�[�h�̐������Afalse���󂯎��ƃ}�[�N��Ԃ�
        if (flag) {
            return "(" + this.number + ")";
        } else {
            return "(" + this.mark + ")";
        }
    }
}
