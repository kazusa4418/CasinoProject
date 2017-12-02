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
                System.out.print("“ü—Í‚³‚ê‚½’l‚ª³‚µ‚­‚ ‚è‚Ü‚¹‚ñB\nÄ“x“ü—Í‚µ‚Ä‚­‚¾‚³‚¢\n> ");
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("NULL‚É§Œä‚ªˆÚ‚Á‚Ä‚¢‚Ü‚·");
        return null;
    }

    public int scanInt() {
        return Integer.parseInt(scanLine("[0-9]+"));
    }

    public int scanInt(String format) {
        return Integer.parseInt(scanLine(format));
    }
}
