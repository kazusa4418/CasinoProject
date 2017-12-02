package playingcard;

/**
 * ���̗񋓌^�N���X�̓g�����v�ɂ����鐔������������N���X�ł��B
 * @author kazusa4418
 * @see Card
 * @see CardMark
 * @since 1.0
 * @deprecated Card�N���X�𗘗p���Ă�������
 */
public enum CardNumber {
    num1("A"),           //ordinal is 0
    num2("2"),           //ordinal is 1
    num3("3"),           //ordinal is 2
    num4("4"),           //ordinal is 3
    num5("5"),           //ordinal is 4
    num6("6"),           //ordinal is 5
    num7("7"),           //ordinal is 6
    num8("8"),           //ordinal is 7
    num9("9"),           //ordinal is 8
    num10("10"),         //ordinal is 9
    num11("J"),          //ordinal is 10
    num12("Q"),          //ordinal is 11
    num13("K"),          //ordinal is 12
    JOKER("JOKER");      //ordinal is 13

    /**
     * ����Enum�N���X�̃C���X�^���X�̃g�����v�̐���������String�^�ϐ��ł��B
     * �e�����ɑΉ����镶����܂��͐�����String�Ƃ��Ċi�[���Ă��܂��B
     */
    private String number;

    /**
     * ���̃R���X�g���N�^�͂���Enum�̃C���X�^���X�����ꂽ�ۂ�String�^�̕ϐ����󂯎��
     * �t�B�[���h�ł���number�֊i�[���܂��B
     *
     * @param number - �󂯎����������͂��̃C���X�^���X�̐����̌����ڂɂȂ�܂��B
     */
    CardNumber(String number) {
        this.number = number;
    }

    /**
     * ���̃C���X�^���X�̎������̌����ڂ�String�ŕԋp���郁�\�b�h�ł��B
     * num1�̃C���X�^���X�ł����A��Ԃ��Anum2�ł����2��Ԃ��܂��B
     *
     * @return ���̃C���X�^���X�̐����̌����ڂ�Ԃ��܂��B
     */
    public String toString() {
        return this.number;
    }
}
