package card.element;

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

    private String number;

    CardNumber(String number) {
        this.number = number;
    }

    public String toString() {
        return this.number;
    }
}
