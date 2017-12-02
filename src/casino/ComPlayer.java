package casino;

import playingcard.*;
import java.util.ArrayList;
import java.util.List;

public class ComPlayer {
    private Hand hand = new Hand();

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    //��D�����J���郁�\�b�h
    public void showHand() {
        for (Card card : this.hand) {
            System.out.print(card);
        }
        System.out.println();
    }

    //�Q�[���ɂ���Ď�D�̌��J���@���ς��
    public void showHand(String gameMode) {
        switch (gameMode) {
            case "runBlackJack":
                System.out.println(this.hand.get(0) + "(BACK OF CARD)");
                break;
        }
    }

    public void handChengeOnPoker() {
        //����ւ����D���i�[���郊�X�g
        List<Boolean> result = handCheckOnPoker(this.hand);

        //�󂯎�������X�g��p���āA��D�̃J�[�h�����ւ���
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i)) {
                hand.set(i, PokerManager.stock.getCard());
            }
        }
    }

    private List<Boolean> handCheckOnPoker(Hand hand) {
        List<Boolean> flag = new ArrayList<>();
        int jokerCounter = 0;
        int counter = 0;

        //��D�ɂ���JOKER�̖����𐔂���
        for (Card aHand : hand) {
            if (aHand.getNumber() == CardNumber.JOKER) {
                jokerCounter++;
            }
        }
        /*��D�̒��g���m�F���āA�������̂ɑ���Ȃ��J�[�h�̖������J�E���g����
         *���̌��ʂ����X�g�Ɋi�[����B
         *���X�g���g���Ăǂ̖������ɍs�������ʂ���
         */

        //���̕����͎�������ϓ���A���Ԃ������肻���Ȃ��ߕʓr�쐬���邱�Ƃɂ���
        //����Ė������Ƃ��A�A�b�v�f�[�g�Ƃ����`�Ŏ������邱�Ƃɂ���
        //DEBUG
        flag.add(false);
        flag.add(true);
        flag.add(true);
        flag.add(false);
        flag.add(false);

        return flag;
    }
}
