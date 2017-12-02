package playingcard;

public enum CardMark {
    //トランプのマークを表すenumクラス
    SPADE("スペード"),     //ordinal is 0
    HEART("ハート"),       //ordinal is 1
    DIAMOND("ダイヤ"),     //ordinal is 2
    CLOVER("クローバー"),  //ordinal is 3
    JOKER("JOKER");        //ordinal is 4

    private String mark;

    CardMark(String mark) {
        this.mark = mark;
    }

    public String toString() {
        return this.mark;
    }
}
