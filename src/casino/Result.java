package casino;

import playingcard.CardNumber;
import playingcard.CardSuit;

class Result {
    /*このクラスはPokerクラスで手札の役を調べた際に
     *呼び出し元のPokerManagerクラスに結果を返すときに用いられるクラス
     */

    private int strong;                //役の強さ
    private CardNumber firstStrong;    //役が同じ時に比べる一番強いカードの数値
    private CardNumber secondStrong;   //二番目に強いカードの数値
    private int jokerNumber;           //手札のジョーカーの数
    private CardSuit mark;             //マークの強さ

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
