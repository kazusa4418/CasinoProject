package util;

import java.io.IOException;

/**
 * ConsoleControlクラスは実装することでコンソール画面を制御できるように拡張します。
 *
 * @author kazusa4418
 * @see Runtime
 */
public class ConsoleControl {

    /**
     * clearScreenメソッドはコンソールの出力をクリアするメソッドです。
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
     * sleepメソッドは引数で指定された秒数スレッドをスリープします。
     *
     * @param millis - int型変数でスレッドをスリープする時間をミリ秒で指定します。
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
