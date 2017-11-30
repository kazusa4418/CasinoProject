package system.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputScanner extends BufferedReader {
    String format;

    public InputScanner(String format) {
        super(new InputStreamReader(System.in));
        this.format = format;
    }

    public String scanLine() {
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
            System.out.println("ｷｰﾎﾞｰﾄﾞからの読み込みに失敗しました。\nシステムを強制終了します。");
            System.exit(1);
        }
        System.out.println("NULLに制御が移っています");
        return null;
    }

    public int scanInt() {
        return Integer.parseInt(scanLine());
    }
}
