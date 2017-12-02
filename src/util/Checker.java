package util;

import java.util.Scanner;

public class Checker {
    private static Scanner sc = new Scanner(System.in);

    public static int numberCheck(int number, int count) {
        //numberが1~countまでの整数かどうかを判別します
        while (true) {
            if (number > 0 && number <= count) {
                return number;
            }
            System.out.print("入力が間違っています。\n再度入力してください。\n> ");
            number = Integer.parseInt(stringCheck(sc.nextLine(), "[0-9]+"));
        }
    }

    public static int numberCheck(String number, int count) {
        //Stringで数が与えられても対応します。
        number = stringCheck(number, "[0-9]+");
        return numberCheck(Integer.parseInt(number), count);
    }

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
