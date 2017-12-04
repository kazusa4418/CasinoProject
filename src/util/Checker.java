package util;

import java.util.Scanner;

/**
 * Checker�N���X�͓��͂ɑ΂��Đ����������܂��B
 * �W�����͂Ȃǂ���󂯎�����l���w�肵���t�H�[�}�b�g�Ɉ�v���Ă��邩���ʂ��܂��B
 * �s��v�������ꍇ�A�ē��͂����߂܂��B
 *
 * @author kazusa4418
 * @see Scanner
 */
public class Checker {
    //�W�����͂��󂯕t����ϐ�sc
    private static Scanner sc = new Scanner(System.in);

    /**
     * numberCheck���\�b�h�͎󂯎����int�^�ϐ����w�肳�ꂽ�l�͈͓̔��ɂ��邩�������܂��B
     * ���͂��ꂽ�l��0~�����Ŏw�肵���l�͈̔͂ɂ����܂��Ă��Ȃ���΍ē��͂����߂܂��B
     *
     * @param number - �W�����͂Ŏ󂯎����int�^�ϐ��ł��B
     * @param range - �͈͂Ɏw�肷��int�^�ϐ��ł��B
     * @return ���͂��ꂽ�l���͈͂𖞂����Ă����炻�̒l��Ԃ��܂��B
     */
    public static int numberCheck(int number, int range) {
        //number��1~count�܂ł̐������ǂ����𔻕ʂ��܂�
        return numberCheck(number, 0, range);
    }

    /**
     * numberCheck���\�b�h�͎󂯎����int�^�ϐ����w�肳�ꂽ�l�͈͓̔��ɂ��邩�������܂��B
     * ���͂��ꂽ�l�������Ŏw�肵��2��int�^�����͈͓̔��ɂ����܂��Ă��Ȃ���΍ē��͂����߂܂��B
     *
     * @param number - �W�����͂Ŏ󂯎����int�^�ϐ��ł��B
     * @param fromRange - �����͈̔͂��w�肷��int�^�ϐ��ł��B
     * @param toRange - ����͈̔͂��w�肷��int�^�ϐ��ł��B
     * @return ���͂��ꂽ�l���͈͂𖞂����Ă����炻�̒l��Ԃ��܂��B
     */
    public static int numberCheck(int number, int fromRange, int toRange) {
        while (true) {
            if (number > fromRange && number <= toRange) {
                return number;
            }
            System.out.print("���͂��Ԉ���Ă��܂��B\n�ēx���͂��Ă��������B\n> ");
            number = Integer.parseInt(stringCheck(sc.nextLine(), "[0-9]+"));
        }

    }

    /**
     * numberCheck���\�b�h�͎󂯎����String���w�肳�ꂽint�^�ϐ��͈͓̔��ɂ��邩�������܂��B
     * �󂯂Ƃ���String�^�I�u�W�F�N�g��������parse�ł��Ȃ������ꍇ�A�܂���
     * ������parse�����I�u�W�F�N�g��int�^�ϐ��Ŏw�肵���l�͈͓̔��ɂ����܂��Ă��Ȃ������ꍇ
     * �ē��͂����߂܂��B
     *
     * @param number - ���ʂ����String�^�I�u�W�F�N�g�ł��B
     * @param range - �͈͂��w�肷��int�^�ϐ��ł��B
     * @return ���͂��ꂽ�l���͈͂𖞂����Ă����炻�̒l��Ԃ��܂��B
     */
    public static int numberCheck(String number, int range) {
        number = stringCheck(number, "[0-9]+");
        return numberCheck(Integer.parseInt(number), 0, range);
    }

    /**
     * numberCheck���\�b�h�͎󂯎����String���w�肳�ꂽint�^�ϐ��͈͓̔��ɂ��邩�������܂��B
     * �󂯎����String�^�I�u�W�F�N�g��������parse�ł��Ȃ������ꍇ�A�܂���
     * ������parse�����I�u�W�F�N�g��int�^�ϐ��Ŏw�肵���l�͈͓̔��ɂ����܂��Ă��Ȃ������ꍇ
     * �ē��͂����߂܂��B
     *
     * @param number - ���ʂ����String�^�I�u�W�F�N�g�ł��B
     * @param fromRange - �����͈̔͂��w�肷��int�^�ϐ��ł��B
     * @param toRange - ����͈̔͂��w�肷��int�^�ϐ��ł��B
     * @return ���͂��ꂽ�l���͈͂𖞂����Ă����炻�̒l��Ԃ��܂��B
     */
    public static int numberCheck(String number, int fromRange, int toRange) {
        number = stringCheck(number, "[0-9]+");
        return numberCheck(Integer.parseInt(number), fromRange, toRange);
    }

    /**
     * stringCheck���\�b�h�͎󂯎����String�^�I�u�W�F�N�g���w�肳�ꂽ�t�H�[�}�b�g�𖞂����Ă��邩�������܂��B
     * �󂯎����String�^�I�u�W�F�N�g���w�肳�ꂽ�t�H�[�}�b�g�Ƀ}�b�`���Ȃ������ꍇ�A
     * �ē��͂����߂܂��B
     *
     * @param sentence - �������s��String�^�I�u�W�F�N�g�ł��B
     * @param format - String�^�I�u�W�F�N�g�Ō����Ɏg�p����t�H�[�}�b�g���w�肵�܂��B
     * @return ���͂��ꂽ�I�u�W�F�N�g���t�H�[�}�b�g�𖞂����Ă����炻�̃I�u�W�F�N�g��Ԃ��܂��B
     */
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
