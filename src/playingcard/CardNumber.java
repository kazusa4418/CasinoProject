package playingcard;

/**
 * ���̗񋓌^�N���X�̓g�����v�ɂ����鐔������������N���X�ł��B
 * �e������ordinal�̒l�͈�v���܂����AJOKER�̂�ordinal = 0�ƂȂ�܂��B
 *
 * @author kazusa4418
 * @see Enum
 * @see Card
 * @see CardSuit
 */
public enum CardNumber {
    JOKER("JOKER"),      //ordinal is 0
    num1("A"),           //ordinal is 1
    num2("2"),           //ordinal is 2
    num3("3"),           //ordinal is 3
    num4("4"),           //ordinal is 4
    num5("5"),           //ordinal is 5
    num6("6"),           //ordinal is 6
    num7("7"),           //ordinal is 7
    num8("8"),           //ordinal is 8
    num9("9"),           //ordinal is 9
    num10("10"),         //ordinal is 10
    num11("J"),          //ordinal is 11
    num12("Q"),          //ordinal is 12
    num13("K");          //ordinal is 13

    //��ʂɏo�͂���镶����(num1�ł����A)
    private String number;

    //�C���X�^���X�����ꂽ�Ƃ��Ɋe�v�f�ɑΉ����鐔���̕������ێ�����
    //����ɂ����print���ꂽ�Ƃ��Ƀg�����v�̐����Ɠ����\�L�ŏo�͂��邱�Ƃ��ł���
    CardNumber(String number) {
        this.number = number;
    }

    /**
     * �e�C���X�^���X�ɑΉ�����g�����v�̐�����ԋp���܂��B
     *
     * @return �g�����v�̐�����String�^�ŕԋp���܂��B
     */
    @Override
    public String toString() {
        return this.number;
    }
}
