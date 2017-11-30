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
            System.out.print("“ü—Í‚ª³‚µ‚­‚ ‚è‚Ü‚¹‚ñB\nÄ“x“ü—Í‚µ‚Ä‚­‚¾‚³‚¢\n> ");
            sentence = sc.nextLine();
        }
    }
}
