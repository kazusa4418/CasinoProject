package playingcard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * CardStock�N���X��Card�N���X�̃I�u�W�F�N�g�𕡐��ێ����ĎR�D���������܂��B
 *
 * @author kazusa4418
 * @see Card
 * @see ArrayList
 */
public class CardStock extends ArrayList<Card> {

    /**
     * JOKER�������g�����v�Ŏg����J�[�h���ׂĂ�Card�^�I�u�W�F�N�g�Ƃ��č쐬��List�ɕێ����܂��B
     * �g�����v�̎R�D����铮����������܂��B
     * �f�t�H���g�ł�JOKER��2����������Ԃō쐬����܂��B
     * �����̃J�[�h�̕��т͐��̏�������,�}�[�N�̎ア���Ń\�[�g����Ă��܂��B
     *
     * @author kazusa4418
     * @see Card
     * @see ArrayList
     */
    public CardStock() {
        this(2);
    }

    /**
     * JOKER�������g�����v�Ŏg����J�[�h���ׂĂ�Card�^�I�u�W�F�N�g�Ƃ��č쐬��List�ɕێ����܂��B
     * �g�����v�̎R�D����铮����������܂��B
     * �����ɂ͎R�D�ɂ����JOKER�̖������w�肵�܂��B
     * �����ɕ��̐����n���ꂽ�ꍇ�ɂ�JOKER��0���̏�ԂŃC���X�^���X�����܂��B
     *
     * @param joker_number - �R�D�ɂ����JOKER�̖�����int�^�ϐ��Ŏw�肷��
     */
    public CardStock(int joker_number) {
        //52�����̃J�[�h�̊e�C���X�^���X���쐬���A���X�g�Ɋi�[����
        CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < 13; i++) {
            this.add(new Card(numbers[i], CardSuit.SPADE));
            this.add(new Card(numbers[i], CardSuit.HEART));
            this.add(new Card(numbers[i], CardSuit.DIAMOND));
            this.add(new Card(numbers[i], CardSuit.CLOVER));
        }
        for (int i = 0; i < joker_number; i++) {
            this.add(new Card(CardNumber.JOKER, CardSuit.JOKER));
        }
    }


    /**
     * ���X�g��0�Ԗڂ�Card�^�I�u�W�F�N�g��Ԃ��܂��B
     * �R�D����ꖇ�J�[�h������������������܂��B
     * CardStock�I�u�W�F�N�g�ɗv�f�����݂��Ȃ������ꍇ��O���X���[����܂��B
     *
     * @return Card�^�I�u�W�F�N�g��Ԃ��܂��B
     * @throws ArrayIndexOutOfBoundsException �v�f�����݂��Ȃ��ꍇ��takeCard���\�b�h���Ă΂ꂽ�ꍇ
     */
    public Card takeCard() {
        Card card = this.get(0);
        this.remove(0);
        return card;
    }

    /**
     * ���X�g�̗v�f�������_���ɓ���ւ��܂��B
     * �R�D���V���b�t�����铮����������܂��B
     */
    public void shuffle() {
        Collections.shuffle(this);
    }
}
