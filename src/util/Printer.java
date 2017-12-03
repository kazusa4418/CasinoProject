package util;

import java.util.Scanner;

/**
 * PrinterクラスはPrintStreamの出力系メソッドを拡張します。
 * このクラスに定義されているそれぞれのメソッドは出力を受け付けた後に
 * 何かしらのキー入力を要求します。
 * 出力後キー入力を受け付けるまでスレッドを止めることができます。
 *
 * @author kazusa4418
 * @see Scanner
 * @see System
 * @see java.io.PrintStream
 */
public class Printer {
    //標準入力を行うための変数sc
    private Scanner sc = new Scanner(System.in);

    /**
     * PrintStreamのメソッドprintを拡張します。
     * 受け取ったオブジェクトの出力のあとキー入力を要求します。
     *
     * @param o - 出力されるObject
     */
    public void print(Object o) {
        System.out.print(o);
        sc.nextLine();
    }

    /**
     * PrintStreamのメソッドprintlnを拡張します。
     * 受け取ったオブジェクトの出力のあとキー入力を要求します。
     *
     * @param o - 出力されるObject
     */
    public void println(Object o) {
        System.out.println(o);
        sc.nextLine();
    }

    /**
     * キー入力を要求します。
     */
    public void pleaseEnter() {
        sc.nextLine();
    }
}
