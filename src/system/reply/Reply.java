package system.reply;

import java.util.Scanner;

public class Reply {
    private static Scanner sc = new Scanner(System.in);

    public static boolean yesOrNo(String sentence) {
        while (true) {
            if (sentence.equalsIgnoreCase("yes")) {
                return true;
            }
            if (sentence.equalsIgnoreCase("no")) {
                return false;
            }
            System.out.print("入力が正しくありません。\n再度入力してください\n> ");
            sentence = sc.nextLine();
        }
    }
}
