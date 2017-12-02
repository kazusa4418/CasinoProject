package playingcard;

/**
 * この列挙型クラスはトランプにおける数字を実現するクラスです。
 * @author kazusa4418
 * @see Card
 * @see CardMark
 * @since 1.0
 * @deprecated Cardクラスを利用してください
 */
public enum CardNumber {
    num1("A"),           //ordinal is 0
    num2("2"),           //ordinal is 1
    num3("3"),           //ordinal is 2
    num4("4"),           //ordinal is 3
    num5("5"),           //ordinal is 4
    num6("6"),           //ordinal is 5
    num7("7"),           //ordinal is 6
    num8("8"),           //ordinal is 7
    num9("9"),           //ordinal is 8
    num10("10"),         //ordinal is 9
    num11("J"),          //ordinal is 10
    num12("Q"),          //ordinal is 11
    num13("K"),          //ordinal is 12
    JOKER("JOKER");      //ordinal is 13

    /**
     * このEnumクラスのインスタンスのトランプの数字を持つString型変数です。
     * 各数字に対応する文字列または数字をStringとして格納しています。
     */
    private String number;

    /**
     * このコンストラクタはこのEnumのインスタンスが作られた際にString型の変数を受け取り
     * フィールドであるnumberへ格納します。
     *
     * @param number - 受け取った文字列はこのインスタンスの数字の見た目になります。
     */
    CardNumber(String number) {
        this.number = number;
    }

    /**
     * このインスタンスの持つ数字の見た目をStringで返却するメソッドです。
     * num1のインスタンスであればAを返し、num2であれば2を返します。
     *
     * @return このインスタンスの数字の見た目を返します。
     */
    public String toString() {
        return this.number;
    }
}
