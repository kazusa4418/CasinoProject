package playingcard;

public class CardSorterOnPoker extends CardSorter {
    public int compare(Card card1, Card card2) {
        //�����J�[�h�ł���Ȃ�0��Ԃ�
        if (card1.equals(card2)) {
            return 0;
        }
        //���g�̃C���X�^���X��A�Ȃ��1��Ԃ�
        if (card1.getNumber() == CardNumber.num1) {
            return 1;
        }
        //��r�Ώۂ̃C���X�^���X��A�Ȃ��-1��Ԃ�
        if (card2.getNumber() == CardNumber.num1) {
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
        if (card1.getMark().ordinal() > card2.getMark().ordinal()) {
            return 1;
        } else {
            return -1;
        }
    }
}
