package card;

import card.element.CardMark;
import card.element.CardNumber;
import card.sorter.CardSorter;

import java.util.ArrayList;
import java.util.Collections;

public class CardStock extends ArrayList<Card> {

    //�������Ȃ��ꍇ�̓W���[�J�[��񖇊܂ނ��̂Ƃ��ăX�^�b�N�𐶐�����
    public CardStock() {
        this(2);
    }

    public CardStock(int joker_number) {
        //52�����̃J�[�h�̊e�C���X�^���X���쐬���A���X�g�Ɋi�[����
        CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < 13; i++) {
            this.add(new Card(numbers[i], CardMark.SPADE));
            this.add(new Card(numbers[i], CardMark.HEART));
            this.add(new Card(numbers[i], CardMark.DIAMOND));
            this.add(new Card(numbers[i], CardMark.CLOVER));
        }
        for (int i = 0; i < joker_number; i++) {
            this.add(new Card(CardNumber.JOKER, CardMark.JOKER));
        }
        sort();
    }

    //�X�^�b�N����J�[�h���ꖇ�擾���郁�\�b�h
    public Card getCard() {
        Card card = this.get(0);
        this.remove(0);
        return card;
    }

    //�X�^�b�N���V���b�t�����郁�\�b�h
    public void shuffle() {
        Collections.shuffle(this);
    }

    //�X�^�b�N���\�[�g����->���̑傫����->�}�[�N�̋�����
    private void sort() {
        Collections.sort(this, new CardSorter());
    }
}
