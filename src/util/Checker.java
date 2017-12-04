package util;

import java.util.Scanner;

/**
 * Checkerクラスは入力に対して制限をかけます。
 * 標準入力などから受け取った値が指定したフォーマットに一致しているか判別します。
 * 不一致だった場合、再入力を求めます。
 *
 * @author kazusa4418
 * @see Scanner
 */
public class Checker {
    //標準入力を受け付ける変数sc
    private static Scanner sc = new Scanner(System.in);

    /**
     * numberCheckメソッドは受け取ったint型変数が指定された値の範囲内にあるか検査します。
     * 入力された値が0~引数で指定した値の範囲におさまっていなければ再入力を求めます。
     *
     * @param number - 標準入力で受け取ったint型変数です。
     * @param range - 範囲に指定するint型変数です。
     * @return 入力された値が範囲を満たしていたらその値を返します。
     */
    public static int numberCheck(int number, int range) {
        //numberが1~countまでの整数かどうかを判別します
        return numberCheck(number, 0, range);
    }

    /**
     * numberCheckメソッドは受け取ったint型変数が指定された値の範囲内にあるか検査します。
     * 入力された値が引数で指定した2つのint型整数の範囲内におさまっていなければ再入力を求めます。
     *
     * @param number - 標準入力で受け取ったint型変数です。
     * @param fromRange - 下限の範囲を指定するint型変数です。
     * @param toRange - 上限の範囲を指定するint型変数です。
     * @return 入力された値が範囲を満たしていたらその値を返します。
     */
    public static int numberCheck(int number, int fromRange, int toRange) {
        while (true) {
            if (number > fromRange && number <= toRange) {
                return number;
            }
            System.out.print("入力が間違っています。\n再度入力してください。\n> ");
            number = Integer.parseInt(stringCheck(sc.nextLine(), "[0-9]+"));
        }

    }

    /**
     * numberCheckメソッドは受け取ったStringが指定されたint型変数の範囲内にあるか検査します。
     * 受けとったString型オブジェクトが整数にparseできなかった場合、または
     * 整数にparseしたオブジェクトがint型変数で指定した値の範囲内におさまっていなかった場合
     * 再入力を求めます。
     *
     * @param number - 判別されるString型オブジェクトです。
     * @param range - 範囲を指定するint型変数です。
     * @return 入力された値が範囲を満たしていたらその値を返します。
     */
    public static int numberCheck(String number, int range) {
        number = stringCheck(number, "[0-9]+");
        return numberCheck(Integer.parseInt(number), 0, range);
    }

    /**
     * numberCheckメソッドは受け取ったStringが指定されたint型変数の範囲内にあるか検査します。
     * 受け取ったString型オブジェクトが整数にparseできなかった場合、または
     * 整数にparseしたオブジェクトがint型変数で指定した値の範囲内におさまっていなかった場合
     * 再入力を求めます。
     *
     * @param number - 判別されるString型オブジェクトです。
     * @param fromRange - 下限の範囲を指定するint型変数です。
     * @param toRange - 上限の範囲を指定するint型変数です。
     * @return 入力された値が範囲を満たしていたらその値を返します。
     */
    public static int numberCheck(String number, int fromRange, int toRange) {
        number = stringCheck(number, "[0-9]+");
        return numberCheck(Integer.parseInt(number), fromRange, toRange);
    }

    /**
     * stringCheckメソッドは受け取ったString型オブジェクトが指定されたフォーマットを満たしているか検査します。
     * 受け取ったString型オブジェクトが指定されたフォーマットにマッチしなかった場合、
     * 再入力を求めます。
     *
     * @param sentence - 検査を行うString型オブジェクトです。
     * @param format - String型オブジェクトで検査に使用するフォーマットを指定します。
     * @return 入力されたオブジェクトがフォーマットを満たしていたらそのオブジェクトを返します。
     */
    public static String stringCheck(String sentence, String format) {
        while (true) {
            if (sentence.matches(format)) {
                return sentence;
            }
            System.out.print("入力された文字のフォーマットがあっていません。\n再度入力してください。\n> ");
            sentence = sc.nextLine();
        }
    }
}
