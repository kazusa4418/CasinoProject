package poker;

import card.Card;
import card.element.CardNumber;
import card.element.CardMark;
import player.hand.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Poker {
    //�󂯎����Hand�^�ϐ���p���Ď�D�ɂ�����Ă�����𔻒肷��
    static Result handCheck(Hand hand) {
        /*�|�[�J�[�̖��ɂ��Ă͉��L�T�C�g���Q�l�ɂ��܂���
         *http://ww6.tiki.ne.jp/~tamatsuo/poker.htm
         */

        //�u�^ == 0
        //�����y�A == 1
        //�c�[�y�A == 2
        //�X���[�J�[�h == 3
        //�X�g���[�g == 4
        //�t���b�V�� == 5
        //�t���n�E�X == 6
        //�t�H�[�J�[�h == 7
        //�X�g���[�g�t���b�V�� == 8
        //���C�����X�g���[�g�t���b�V�� == 9
        //�t�@�C�u�J�[�h == 10
        int jokerCounter = 0;

        //��D�̍ŏ��̃J�[�h�̐����ƃ}�[�N��ۑ����Ă���
        CardNumber firstNumber = hand.get(0).getNumber();
        CardMark firstMark = hand.get(0).getMark();
        //�t���b�V����������Ă�����true������
        boolean flush = true;
        //�X�g���[�g��������Ă�����true������
        boolean straight = true;

        //��D�ɂ���JOKER�̖����𐔂���
        //��������łɎ�D��JOKER�͂��ׂč폜����
        for (Card aHand : hand) {
            if (aHand.getNumber() == CardNumber.JOKER) {
                jokerCounter++;
            }
        }
        final int JOKER_NUMBER = jokerCounter;

        //�t���b�V���̖���������Ă��邩���肷��
        for (Card card : hand) {
            if (!((card.getMark() == firstMark) || card.getNumber() == CardNumber.JOKER)) {
                flush = false;
                break;
            }
        }
        //�X�g���[�g�̖���������Ă��邩���肷��
        int num = 0;
        for (int i = 0; i < hand.size() - JOKER_NUMBER; i++) {
            if (hand.get(i).getNumber().ordinal() != (firstNumber.ordinal() + num)) {
                if (jokerCounter > 0) {
                    jokerCounter--;
                    i--;
                } else {
                    straight = false;
                    break;
                }
            }
            num++;
        }
        //�X�g���[�g�t���b�V���ƃ��C�����X�g���[�g�t���b�V���𔻒肷��
        if (flush && straight) {
            if (firstNumber.ordinal() == 8) {
                //���C�����X�g���[�g�t���b�V������
                return new Result(9, JOKER_NUMBER, firstMark);
            }
            //�X�g���[�g�t���b�V������
            return new Result(8, firstNumber, JOKER_NUMBER, firstMark);
        }
        if (straight) {
            //�X�g���[�g����
            return new Result(4, firstNumber, hand.get(hand.size() - 1).getMark());
        }


        /*��D�̒��̃J�[�h�ŉ��������J�[�h�����邩���肷��
         *���肵�������͎�D�ꖇ���ƂɋL�^���Ă���
         *�S�̓I�ɃR�[�h���璷�Ȃ̂Ō�Œ������邱��
         */
        int counter = 0;
        List<Integer> matchNumber = new ArrayList<>();
        for (int i = 0; i < hand.size() - JOKER_NUMBER; i++) {
            for (int j = 0; j < hand.size() - JOKER_NUMBER; j++) {
                if (hand.get(i).getNumber() == hand.get(j).getNumber()) {
                    counter++;
                }
            }
            matchNumber.add(counter);
            counter = 0;
        }
        //������matchNumber(��D���Ƃɔ��肵�����������̐�)�̒��̍ő�l�̒l�𒲂ׂ�
        int max = 0;
        for (Integer i : matchNumber) {
            max = Math.max(max, i);
        }
        //���׏I������炻�̍ő�l�̊i�[����Ă���index�𒲂ׂ�
        //secondStrong�̓c�[�y�A����̂Ƃ��̂ݎg����ϐ�
        int index = matchNumber.lastIndexOf(max);
        CardNumber firstStrong = hand.get(index).getNumber();
        index = matchNumber.indexOf(max);
        CardNumber secondStrong = hand.get(index).getNumber();

        Collections.sort(matchNumber);
        //JOKER����ԓ��������̑��������J�[�h�����ɂ���
        if (matchNumber.get(matchNumber.size() - 1) == 1) {
            //�W���[�J�[��������D���u�^��Ԃł���΃����y�A����ɂ��邽�߂ɍŌ�̗v�f����JOKER������
            int i = matchNumber.get(matchNumber.size() - 1) + JOKER_NUMBER;
            matchNumber.set(matchNumber.size() - 1, i);
        } else {
            int i = 1;
            while (i <= max) {
                int number = matchNumber.get(matchNumber.size() - i) + JOKER_NUMBER;
                matchNumber.set(matchNumber.size() - i, number);
                i++;
            }
        }
        //��ԏd���̑��������J�[�h�̖������擾����
        int firstMany = matchNumber.get(matchNumber.size() - 1);

        switch (firstMany) {
            case 1:
                if (flush) {
                    //�t���b�V������
                    return new Result(5, hand.get(hand.size() - 1).getNumber(), JOKER_NUMBER, firstMark);
                }
                //�u�^(�n�C�J�[�h)����
                return new Result(0, firstStrong, firstMark);
            case 2:
                if (flush) {
                    //�t���b�V������
                    return new Result(5, hand.get(hand.size() - 1).getNumber(), JOKER_NUMBER, firstMark);
                }
                //�c�[�y�A��������Ă��邩�𔻒�
                if (matchNumber.get(matchNumber.size() - 3) == 2) {
                    //�c�[�y�A����
                    return new Result(2, firstStrong, secondStrong, JOKER_NUMBER, firstMark);
                }
                //�����y�A����
                return new Result(1, firstStrong, JOKER_NUMBER, firstMark);
            case 3:
                //�t���n�E�X��������Ă��邩����
                if (matchNumber.contains(2)) {
                    System.out.println("DEBUG: �t���n�E�X���肪�ǂ܂�Ă��܂�");
                    //�t���n�E�X����
                    return new Result(6, firstStrong, JOKER_NUMBER, firstMark);
                }
                if (flush) {
                    //�t���b�V������
                    return new Result(5, hand.get(hand.size() - 1).getNumber(), JOKER_NUMBER, firstMark);
                }
                //�X���[�J�[�h����
                return new Result(3, firstStrong, JOKER_NUMBER, firstMark);
            case 4:
                //�t�H�[�J�[�h����
                return new Result(7, firstStrong, JOKER_NUMBER, firstMark);
            case 5:
                //�t�@�C�u�J�[�h����
                return new Result(10, firstStrong, JOKER_NUMBER, firstMark);
        }
        return null;
    }
}
