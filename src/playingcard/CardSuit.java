package playingcard;

/**
 * この列挙型クラスはトランプにおけるマークを実現するクラスです。
 * 各マークの強さはordinalの大きさと一致します。
 *
 * @author kazusa4418
 * @see Enum
 * @see Card
 * @see CardNumber
 */
public enum CardSuit {
    SPADE("スペード"),     //ordinal is 0
    HEART("ハート"),       //ordinal is 1
    DIAMOND("ダイヤ"),     //ordinal is 2
    CLOVER("クローバー"),  //ordinal is 3
    JOKER("JOKER");        //ordinal is 4

    //出力するときにこの変数に格納されている文字列が出力される
    private String suit;

    //出力するときに日本語で出力できるように日本語の表記をコンストラクタで変数suitに持たせておく
    CardSuit(String suit) {
        this.suit = suit;
    }

    /**
     * 各インスタンスに対応するトランプのマークを返却します。
     *
     * @return トランプのマークをString型で返却します。
     */
    @Override
    public String toString() {
        return this.suit;
    }
}
