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
            System.out.print("���͂�����������܂���B\n�ēx���͂��Ă�������\n> ");
            sentence = sc.nextLine();
        }
    }
}
