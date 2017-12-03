package util;

import java.util.Scanner;

/**
 * Printer�N���X��PrintStream�̏o�͌n���\�b�h���g�����܂��B
 * ���̃N���X�ɒ�`����Ă��邻�ꂼ��̃��\�b�h�͏o�͂��󂯕t�������
 * ��������̃L�[���͂�v�����܂��B
 * �o�͌�L�[���͂��󂯕t����܂ŃX���b�h���~�߂邱�Ƃ��ł��܂��B
 *
 * @author kazusa4418
 * @see Scanner
 * @see System
 * @see java.io.PrintStream
 */
public class Printer {
    //�W�����͂��s�����߂̕ϐ�sc
    private Scanner sc = new Scanner(System.in);

    /**
     * PrintStream�̃��\�b�hprint���g�����܂��B
     * �󂯎�����I�u�W�F�N�g�̏o�͂̂��ƃL�[���͂�v�����܂��B
     *
     * @param o - �o�͂����Object
     */
    public void print(Object o) {
        System.out.print(o);
        sc.nextLine();
    }

    /**
     * PrintStream�̃��\�b�hprintln���g�����܂��B
     * �󂯎�����I�u�W�F�N�g�̏o�͂̂��ƃL�[���͂�v�����܂��B
     *
     * @param o - �o�͂����Object
     */
    public void println(Object o) {
        System.out.println(o);
        sc.nextLine();
    }

    /**
     * �L�[���͂�v�����܂��B
     */
    public void pleaseEnter() {
        sc.nextLine();
    }
}
