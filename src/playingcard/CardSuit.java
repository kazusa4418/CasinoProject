package playingcard;

/**
 * ���̗񋓌^�N���X�̓g�����v�ɂ�����}�[�N����������N���X�ł��B
 * �e�}�[�N�̋�����ordinal�̑傫���ƈ�v���܂��B
 *
 * @author kazusa4418
 * @see Enum
 * @see Card
 * @see CardNumber
 */
public enum CardSuit {
    SPADE("�X�y�[�h"),     //ordinal is 0
    HEART("�n�[�g"),       //ordinal is 1
    DIAMOND("�_�C��"),     //ordinal is 2
    CLOVER("�N���[�o�["),  //ordinal is 3
    JOKER("JOKER");        //ordinal is 4

    //�o�͂���Ƃ��ɂ��̕ϐ��Ɋi�[����Ă��镶���񂪏o�͂����
    private String suit;

    //�o�͂���Ƃ��ɓ��{��ŏo�͂ł���悤�ɓ��{��̕\�L���R���X�g���N�^�ŕϐ�suit�Ɏ������Ă���
    CardSuit(String suit) {
        this.suit = suit;
    }

    /**
     * �e�C���X�^���X�ɑΉ�����g�����v�̃}�[�N��ԋp���܂��B
     *
     * @return �g�����v�̃}�[�N��String�^�ŕԋp���܂��B
     */
    @Override
    public String toString() {
        return this.suit;
    }
}
