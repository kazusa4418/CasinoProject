package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputScanner extends BufferedReader {

    public InputScanner() {
        super(new InputStreamReader(System.in));
    }

    public String scanLine() {
        try {
            return this.readLine();
        } catch(IOException e) {
            System.err.println(e);
        }
        return null;
    }
    public String scanLine(String format) {
        try {
            while (true) {
                String text = this.readLine();
                if (text.matches(format)) {
                    return text;
                }
                System.out.print("���͂��ꂽ�l������������܂���B\n�ēx���͂��Ă�������\n> ");
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("NULL�ɐ��䂪�ڂ��Ă��܂�");
        return null;
    }

    public int scanInt() {
        return Integer.parseInt(scanLine("[0-9]+"));
    }

    public int scanInt(String format) {
        return Integer.parseInt(scanLine(format));
    }
}
