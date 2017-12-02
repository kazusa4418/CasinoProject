package util;

import java.util.Scanner;

public class Checker {
    private static Scanner sc = new Scanner(System.in);

    public static int numberCheck(int number, int count) {
        //number��1~count�܂ł̐������ǂ����𔻕ʂ��܂�
        while (true) {
            if (number > 0 && number <= count) {
                return number;
            }
            System.out.print("���͂��Ԉ���Ă��܂��B\n�ēx���͂��Ă��������B\n> ");
            number = Integer.parseInt(stringCheck(sc.nextLine(), "[0-9]+"));
        }
    }

    public static int numberCheck(String number, int count) {
        //String�Ő����^�����Ă��Ή����܂��B
        number = stringCheck(number, "[0-9]+");
        return numberCheck(Integer.parseInt(number), count);
    }

    public static String stringCheck(String sentence, String format) {
        while (true) {
            if (sentence.matches(format)) {
                return sentence;
            }
            System.out.print("���͂��ꂽ�����̃t�H�[�}�b�g�������Ă��܂���B\n�ēx���͂��Ă��������B\n> ");
            sentence = sc.nextLine();
        }
    }
}
