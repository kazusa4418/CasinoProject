package casino;

import playingcard.CardNumber;
import playingcard.CardMark;

class Result {
    /*���̃N���X��Poker�N���X�Ŏ�D�̖��𒲂ׂ��ۂ�
     *�Ăяo������PokerManager�N���X�Ɍ��ʂ�Ԃ��Ƃ��ɗp������N���X
     */

    private int strong;                //���̋���
    private CardNumber firstStrong;    //�����������ɔ�ׂ��ԋ����J�[�h�̐��l
    private CardNumber secondStrong;   //��Ԗڂɋ����J�[�h�̐��l
    private int jokerNumber;           //��D�̃W���[�J�[�̐�
    private CardMark mark;             //�}�[�N�̋���

    Result(int strong, CardNumber firstStrong, CardMark mark) {
        this(strong, firstStrong, CardNumber.num2, 0, mark);
    }
    Result(int strong, int jokerCounter, CardMark mark) {
        this(strong, CardNumber.num2, CardNumber.num2, jokerCounter, mark);
    }
    Result(int strong, CardNumber firstStrong, int jokerCounter, CardMark mark) {
        this(strong, firstStrong, CardNumber.num2, jokerCounter, mark);
    }

    Result(int strong, CardNumber firstStrong, CardNumber secondStrong, int jokerNumber, CardMark mark) {
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

    CardMark getStrongMark() {
        return this.mark;
    }
}
