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
                System.out.print("���͂��ꂽ�l������������܂���B\n�ēx���͂��Ă�������\n> ");
            }
        } catch (IOException e) {
            System.err.println(e);
            System.out.println("���ް�ނ���̓ǂݍ��݂Ɏ��s���܂����B\n�V�X�e���������I�����܂��B");
            System.exit(1);
        }
        System.out.println("NULL�ɐ��䂪�ڂ��Ă��܂�");
        return null;
    }

    public int scanInt() {
        return Integer.parseInt(scanLine());
    }
}
