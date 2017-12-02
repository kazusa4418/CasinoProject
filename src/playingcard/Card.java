package playingcard;

/**
 * Cardクラスはトランプにおけるカードを実現するクラスです。
 *
 * @author kazusa4418
 * @see CardNumber
 * @see CardSuit
 * @see CardStock
 */
public class Card {
    //カードの数字とマークを持つフィールド
    private final CardNumber number;
    private final CardSuit suit;


    /**
     * Card型オブジェクトを作成します。
     * 引数はカードの数字とマークを指定するのに使われます。
     *
     * @param number - カードの数字を指定するCardNumber型オブジェクト
     * @param suit   - カードのマークを指定するCardSuit型オブジェクト
     */
    public Card(CardNumber number, CardSuit suit) {
        this.number = number;
        this.suit = suit;
    }

    /**
     * 指定された値からCard型オブジェクトを作成します。
     * 引数は数字とマークを指定するのに使用しますが数字を指定するならば0~13、
     * マークを指定するならば0~4までの数値しか受け付けません。
     * それ以外の数が入力された場合例外が発生するので一般には使用しないでください。
     *
     * @param number - カードの数字を指定するint型変数 0-13まで使用し0はJOKER
     * @param suit   - カードのマークを指定するint型変数 0-4まで使用し4はJOKER
     * @throws java.lang.ArrayIndexOutOfBoundsException 範囲外の値が引数に指定されたときに発生します。
     * @deprecated 範囲外の引数が与えられると正しく処理できません。
     * 別のコンストラクターを使用することを推奨します。
     */
    public Card(int number, int suit) {
        CardNumber[] numbers = CardNumber.values();
        CardSuit[] suits = CardSuit.values();
        //与えられた引数のどちらかがJOKERを示すものであった場合JOKERカードを作る
        //それ以外であれば与えられた引数から数字とマークをもったカードインスタンスを作る
        if (numbers[number].ordinal() == 0 || suits[suit].ordinal() == 4) {
            this.number = CardNumber.JOKER;
            this.suit = CardSuit.JOKER;
        } else {
            this.number = numbers[number];
            this.suit = suits[suit];
        }
    }

    /**
     * カードの数字をCardNumber型で返します。
     *
     * @return このインスタンスの持つCardNumber型を返します。
     */
    public CardNumber getNumber() {
        return this.number;
    }

    /**
     * カードのマークをCardSuit型で返します。
     *
     * @return このインスタンスの持つCardSuit型を返します。
     */
    public CardSuit getSuit() {
        return this.suit;
    }

    /**
     * このインスタンスと指定されたCard型インスタンスを比較します。
     *
     * @param card - 比較するCard型インスタンスです。
     * @return お互いのインスタンスが同一のものであればtrueを返します。
     */
    boolean equals(Card card) {
        return card.number == this.number && card.suit == this.suit;
    }

    /**
     * カードの数字とマークを表すString型オブジェクトを返します。
     *
     * @return カードの数字とマークがカンマで区切られ「()」で囲まれた状態で表現されます。
     */
    @Override
    public String toString() {
        if (this.suit == CardSuit.JOKER) {
            return "(" + this.suit + ")";
        }
        return "(" + this.number + "," + this.suit + ")";
    }

    /**
     * カードの数字かマークを表すString型オブジェクトを返します。
     * 引数は数字とマークのどちらを表現するかを指定します。
     * trueならば数字を、falseならばマークを指定します。
     *
     * @param flag - 数字とマークどちらかを指定するboolean
     * @return 指定された表現でのString型オブジェクト
     */
    public String toString(boolean flag) {
        //trueを受け取るとカードの数字を、falseを受け取るとマークを返す
        if (flag) {
            return "(" + this.number + ")";
        } else {
            return "(" + this.suit + ")";
        }
    }
}
