package playingcard;
import java.util.Comparator;

/**
 * CardSorter�N���X��List�ȂǂŃ\�[�g���s���ۂɃ\�[�g���@���w�肵�܂��B
 * �\�[�g�����̓g�����v�J�[�h�ɂ����鎩�R�����ł��B
 *
 * @author kazusa4418
 * @see Card
 * @see Comparator
 */
public class CardSorter implements Comparator<Card> {
    /**
     * �����t���̂��߂�2��Card�^�I�u�W�F�N�g���r���܂��B
     *
     * @param card1 - ��r����Card�^�I�u�W�F�N�g�ł��B
     * @param card2 - ��r�����Card�^�I�u�W�F�N�g�ł��B
     * @return �ŏ��̈����� 2 �Ԗڂ̈�����菬�����ꍇ�͕��̐����A
     *         �������������ꍇ�� 0�A�ŏ��̈����� 2 �Ԗڂ̈������傫���ꍇ�͐��̐����B
     */
    public int compare(Card card1, Card card2) {
        //�����J�[�h�ł����0��Ԃ�
        if (card1.equals(card2)) {
            return 0;
        }
        //���g�̃C���X�^���X�̂ق����傫�����Ȃ�1��Ԃ�
        if (card1.getNumber().ordinal() > card2.getNumber().ordinal()) {
            return 1;
        }
        //��r�Ώۂ̃C���X�^���X�̂ق����傫�����Ȃ�-1��Ԃ�
        if (card1.getNumber().ordinal() < card2.getNumber().ordinal()) {
            return -1;
        }
        //������������������}�[�N���g���Ĕ�r����
        return Integer.compare(card1.getSuit().ordinal(), card2.getSuit().ordinal());

    }
}
