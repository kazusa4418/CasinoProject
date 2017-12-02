package playingcard;
import java.util.Comparator;

public class CardSorter implements Comparator<Card> {
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
        return Integer.compare(card1.getMark().ordinal(), card2.getMark().ordinal());

    }
}
