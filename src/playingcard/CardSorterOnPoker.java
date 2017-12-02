package playingcard;

import java.util.Comparator;

/**
 * CardSorterOnPoker��List�ȂǂŃ\�[�g���s���ۂɃ\�[�g���@���w�肵�܂��B
 * �\�[�g�����̓|�[�J�[�̃��[���ɏ]���ăJ�[�h�̋�����r���\�[�g���܂��B
 *
 * @author kazusa4418
 * @see Card
 * @see Comparator
 */
public class CardSorterOnPoker implements Comparator<Card> {
    /**
     * �󂯎����2��Card�^�C���X�^���X���r���܂��B
     *
     * @param card1 - ��r����Card�^�C���X�^���X�ł��B
     * @param card2 - ��r�����Card�^�C���X�^���X�ł��B
     * @return ��r����C���X�^���X���召�ɂ����0, 1, -1 �̂����ꂩ���ԋp����܂��B
     */
    public int compare(Card card1, Card card2) {
        //�����J�[�h�ł���Ȃ�0��Ԃ�
        if (card1.equals(card2)) {
            return 0;
        }
        //���g�̃C���X�^���X��JOKER�Ȃ��1��Ԃ�
        if (card1.getNumber().ordinal() == 0) {
            return 1;
        }
        //��r�Ώۂ̃C���X�^���X��JOKER�Ȃ��-1��Ԃ�
        if (card2.getNumber().ordinal() == 0) {
            return -1;
        }
        //���g�̃C���X�^���X��A�Ȃ��1��Ԃ�
        if (card1.getNumber().ordinal() == 1) {
            return 1;
        }
        //��r�Ώۂ̃C���X�^���X��A�Ȃ��-1��Ԃ�
        if (card2.getNumber().ordinal() == 1) {
            return -1;
        }
        //���g�̃C���X�^���X�̂ق����傫�����Ȃ�1��Ԃ�
        if (card1.getNumber().ordinal() > card2.getNumber().ordinal()) {
            return 1;
        }
        //��r�Ώۂ̃C���X�^���X�̂ق����傫�����Ȃ�-1��Ԃ�
        if (card1.getNumber().ordinal() < card2.getNumber().ordinal()) {
            return -1;
        }
        //�J�[�h�̐����������Ȃ�A�J�[�h�̃}�[�N�ŋ������͂���
        return Integer.compare(card1.getSuit().ordinal(), card2.getSuit().ordinal());
    }
}
