package casino;

import playingcard.CardNumber;
import playingcard.CardSuit;

class Result {
    /*���̃N���X��Poker�N���X�Ŏ�D�̖��𒲂ׂ��ۂ�
     *�Ăяo������PokerManager�N���X�Ɍ��ʂ�Ԃ��Ƃ��ɗp������N���X
     */

    private int strong;                //���̋���
    private CardNumber firstStrong;    //�����������ɔ�ׂ��ԋ����J�[�h�̐��l
    private CardNumber secondStrong;   //��Ԗڂɋ����J�[�h�̐��l
    private int jokerNumber;           //��D�̃W���[�J�[�̐�
    private CardSuit mark;             //�}�[�N�̋���

    Result(int strong, CardNumber firstStrong, CardSuit mark) {
        this(strong, firstStrong, CardNumber.num2, 0, mark);
    }
    Result(int strong, int jokerCounter, CardSuit mark) {
        this(strong, CardNumber.num2, CardNumber.num2, jokerCounter, mark);
    }
    Result(int strong, CardNumber firstStrong, int jokerCounter, CardSuit mark) {
        this(strong, firstStrong, CardNumber.num2, jokerCounter, mark);
    }

    Result(int strong, CardNumber firstStrong, CardNumber secondStrong, int jokerNumber, CardSuit mark) {
        this.strong = strong;
        this.firstStrong = firstStrong;
        this.secondStrong = secondStrong;
        this.jokerNumber = jokerNumber;
        this.mark = mark;
    }

    int getStrong() {
        return this.strong;
    }

    CardNumber getFirstStrong() {
        return this.firstStrong;
    }

    CardNumber getSecondStrong() {
        return this.secondStrong;
    }

    int getJokerNumber() {
        return this.jokerNumber;
    }

    CardSuit getStrongMark() {
        return this.mark;
    }
}
