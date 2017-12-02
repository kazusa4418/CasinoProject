package playingcard;

/**
 * この列挙型クラスはトランプにおける数字を実現するクラスです。
 * 各数字とordinalの値は一致しますが、JOKERのみordinal = 0となります。
 *
 * @author kazusa4418
 * @see Enum
 * @see Card
 * @see CardSuit
 */
public enum CardNumber {
    JOKER("JOKER"),      //ordinal is 0
    num1("A"),           //ordinal is 1
    num2("2"),           //ordinal is 2
    num3("3"),           //ordinal is 3
    num4("4"),           //ordinal is 4
    num5("5"),           //ordinal is 5
    num6("6"),           //ordinal is 6
    num7("7"),           //ordinal is 7
    num8("8"),           //ordinal is 8
    num9("9"),           //ordinal is 9
    num10("10"),         //ordinal is 10
    num11("J"),          //ordinal is 11
    num12("Q"),          //ordinal is 12
    num13("K");          //ordinal is 13

    //画面に出力される文字列(num1であればA)
    private String number;

    //インスタンス化されたときに各要素に対応する数字の文字列を保持する
    //これによってprintされたときにトランプの数字と同じ表記で出力することができる
    CardNumber(String number) {
        this.number = number;
    }

    /**
     * 各インスタンスに対応するトランプの数字を返却します。
     *
     * @return トランプの数字をString型で返却します。
     */
    @Override
    public String toString() {
        return this.number;
    }
}
