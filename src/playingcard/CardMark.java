package playingcard;

public enum CardMark {
    //�g�����v�̃}�[�N��\��enum�N���X
    SPADE("�X�y�[�h"),     //ordinal is 0
    HEART("�n�[�g"),       //ordinal is 1
    DIAMOND("�_�C��"),     //ordinal is 2
    CLOVER("�N���[�o�["),  //ordinal is 3
    JOKER("JOKER");        //ordinal is 4

    private String mark;

    CardMark(String mark) {
        this.mark = mark;
    }

    public String toString() {
        return this.mark;
    }
}
