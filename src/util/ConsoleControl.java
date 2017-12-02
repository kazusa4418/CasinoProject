package util;

import java.io.IOException;

/**
 * ConsoleControl�N���X�͎������邱�ƂŃR���\�[����ʂ𐧌�ł���悤�Ɋg�����܂��B
 *
 * @author kazusa4418
 * @see Runtime
 */
public class ConsoleControl {

    /**
     * clearScreen���\�b�h�̓R���\�[���̏o�͂��N���A���郁�\�b�h�ł��B
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch(IOException e) {
            System.err.println(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * sleep���\�b�h�͈����Ŏw�肳�ꂽ�b���X���b�h���X���[�v���܂��B
     *
     * @param millis - int�^�ϐ��ŃX���b�h���X���[�v���鎞�Ԃ��~���b�Ŏw�肵�܂��B
     * @throws InterruptedException
     */
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.getStackTrace();
        }
    }
}
