package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * InputScanner�N���X��BufferedReader�N���X���g�����A�t�H�[�}�b�g���w�肵�����͂��������܂��B
 *
 * @author kazusa4418
 * @see BufferedReader
 * @see InputStreamReader
 */
public class InputScanner extends BufferedReader {

    /**
     * InputStream�^�I�u�W�F�N�g�������Ɏw�肳�ꂽInputStreamReader�^�I�u�W�F�N�g��p����
     * �f�t�H���g�T�C�Y�̃o�b�t�@�[�Ńo�b�t�@�����O���ꂽ�A�����^���̓X�g���[�����쐬���܂��B
     */
    public InputScanner() {
        super(new InputStreamReader(System.in));
    }

    /**
     * �e�L�X�g�s��ǂݍ��݂܂��B1 �s�̏I�[�́A���s ('\n') ���A���A ('\r')�A
     * �܂��͕��A�Ƃ���ɑ������s�̂����ꂩ�ŔF������܂��B
     *
     * @return �s�̓��e���܂ޕ�����A�������s�̏I�[�����͊܂߂Ȃ��B�X�g���[���̏I���ɒB���Ă���ꍇ�� null
     * @throws IOException ���o�̓G���[�����������ꍇ
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
     *�e�L�X�g�s��ǂݍ��݂܂��B1 �s�̏I�[�́A���s ('\n') ���A���A ('\r')�A
     * �܂��͕��A�Ƃ���ɑ������s�̂����ꂩ�ŔF������܂��B
     * String�^�I�u�W�F�N�g�œǂݍ��ރe�L�X�g�s�̃t�H�[�}�b�g���w�肷�邱�Ƃ��ł��܂��B
     * �t�H�[�}�b�g�Ɋւ��Ă͐��K�\���ɏ]���Ă��������B
     *
     * @param format - String�^�I�u�W�F�N�g�Ńt�H�[�}�b�g���w�肵�܂��B
     * @throws IOException - ���o�̓G���[�����������ꍇ
     * @return �t�H�[�}�b�g�Ƀ}�b�`�����ꍇ����String�^�I�u�W�F�N�g��Ԃ��܂��B
     */
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

    /**
     * �e�L�X�g�s�𐮐��Ƃ��ēǂݍ��݂܂��B1 �s�̏I�[�́A���s ('\n') ���A���A ('\r')�A
     * �܂��͕��A�Ƃ���ɑ������s�̂����ꂩ�ŔF������܂��B
     *�@
     * @throws IOException - ���o�̓G���[�����������ꍇ
     * @return �s�̓��e���܂�int�^�ϐ�
     */
    public int scanInt() {
        return Integer.parseInt(scanLine("[0-9]+"));
    }

    /**
     * �e�L�X�g�s�𐮐��Ƃ��ēǂݍ��݂܂��B1 �s�̏I�[�́A���s ('\n') ���A���A ('\r')�A
     * �܂��͕��A�Ƃ���ɑ������s�̂����ꂩ�ŔF������܂��B
     * ������String�^�I�u�W�F�N�g���󂯎�邱�Ƃœǂݍ��ރe�L�X�g�s�̃t�H�[�}�b�g���w�肷�邱�Ƃ��ł��܂��B
     * �t�H�[�}�b�g�̎w������Ɋւ��Ă͐��K�\���ɏ]���Ă��������B
     *
     * @param format - String�^�I�u�W�F�N�g�Ńt�H�[�}�b�g���w�肵�܂��B
     * @throws IOException - ���o�̓G���[�����������ꍇ
     * @return �s�̓��e���܂�int�^�ϐ�
     */
    public int scanInt(String format) {
        return Integer.parseInt(scanLine(format));
    }
}
