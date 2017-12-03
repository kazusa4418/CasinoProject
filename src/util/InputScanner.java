package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * InputScannerクラスはBufferedReaderクラスを拡張し、フォーマットを指定した入力を実現します。
 *
 * @author kazusa4418
 * @see BufferedReader
 * @see InputStreamReader
 */
public class InputScanner extends BufferedReader {

    /**
     * InputStream型オブジェクトが引数に指定されたInputStreamReader型オブジェクトを用いて
     * デフォルトサイズのバッファーでバッファリングされた、文字型入力ストリームを作成します。
     */
    public InputScanner() {
        super(new InputStreamReader(System.in));
    }

    /**
     * テキスト行を読み込みます。1 行の終端は、改行 ('\n') か、復帰 ('\r')、
     * または復帰とそれに続く改行のいずれかで認識されます。
     *
     * @return 行の内容を含む文字列、ただし行の終端文字は含めない。ストリームの終わりに達している場合は null
     * @throws IOException 入出力エラーが発生した場合
     */
    public String scanLine() {
        try {
            return this.readLine();
        } catch(IOException e) {
            System.err.println(e);
        }
        return null;
    }

    /**
     *テキスト行を読み込みます。1 行の終端は、改行 ('\n') か、復帰 ('\r')、
     * または復帰とそれに続く改行のいずれかで認識されます。
     * String型オブジェクトで読み込むテキスト行のフォーマットを指定することができます。
     * フォーマットに関しては正規表現に従ってください。
     *
     * @param format - String型オブジェクトでフォーマットを指定します。
     * @throws IOException - 入出力エラーが発生した場合
     * @return フォーマットにマッチした場合そのString型オブジェクトを返します。
     */
    public String scanLine(String format) {
        try {
            while (true) {
                String text = this.readLine();
                if (text.matches(format)) {
                    return text;
                }
                System.out.print("入力された値が正しくありません。\n再度入力してください\n> ");
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("NULLに制御が移っています");
        return null;
    }

    /**
     * テキスト行を整数として読み込みます。1 行の終端は、改行 ('\n') か、復帰 ('\r')、
     * または復帰とそれに続く改行のいずれかで認識されます。
     *　
     * @throws IOException - 入出力エラーが発生した場合
     * @return 行の内容を含むint型変数
     */
    public int scanInt() {
        return Integer.parseInt(scanLine("[0-9]+"));
    }

    /**
     * テキスト行を整数として読み込みます。1 行の終端は、改行 ('\n') か、復帰 ('\r')、
     * または復帰とそれに続く改行のいずれかで認識されます。
     * 引数にString型オブジェクトを受け取ることで読み込むテキスト行のフォーマットを指定することができます。
     * フォーマットの指定方式に関しては正規表現に従ってください。
     *
     * @param format - String型オブジェクトでフォーマットを指定します。
     * @throws IOException - 入出力エラーが発生した場合
     * @return 行の内容を含むint型変数
     */
    public int scanInt(String format) {
        return Integer.parseInt(scanLine(format));
    }
}
