package player;

import card.Card;
import blackjack.BlackJackManager;
import player.data.PlayData;
import player.hand.Hand;
import poker.PokerManager;
import system.tools.InputScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Player {
    //�v���C���[�l�[���⏊�L�`�b�v���ȂǕۑ����Ă���N���X
    private PlayData myData;
    //��D
    private Hand hand = new Hand();
    //SPLIT�����Ƃ��̂ݎg����
    private Hand splitHand;

    public Player(PlayData myData) {
        //�Z�[�u�f�[�^�̓ǂݍ���
        this.myData = myData;
    }

    public void showPlayData() {
        System.out.println("******************************\n");
        System.out.println("�v���C���[�l�[��: " + this.myData.getName());
        System.out.println("�����`�b�v��: " + this.myData.getChips());
        System.out.println("�v���C����: " + this.myData.getPlayTime());
        System.out.println("\n******************************\n");
    }

    public Hand getHand() {
        return this.hand;
    }

    public void showHand() {
        for (Card card : this.hand) {
            System.out.print(card);
        }
        System.out.println();
    }

    public PlayData getPlayData() {
        return this.myData;
    }

    public long getChips() {
        return this.myData.getChips();
    }

    public void getChips(int number) {
        this.myData.plusChips(number);
    }

    public void lostChips(int number) {
        this.myData.plusChips(number * -1);
    }

    public int betChips(int number) {
        return this.myData.getChips(number);
    }

    //HIT : �J�[�h���ꖇ����
    public void runHit() {
        hand.add(BlackJackManager.stock.getCard());
    }

    //DOUBLE : �|�������{�ɂ��ăJ�[�h���ꖇ����
    public void runDouble() {
        //�|�������{�ɂ��鑀���}������
        this.runHit();
    }

    //SURRENDER : �|�����𔼕��̂Ăď������~���
    public void runSurrender() {
        //�|�����𔼕��ɂ��鑀���}������
        System.out.println("�|�����𔼕������ď������~��܂��B");
    }

    //SPLIT : ��D��2���̃J�[�h�����������̎��A��D��2�ɕ�������
    public void runSplit() {
        splitHand.add(hand.get(1));
        hand.remove(1);
    }

    //INSURANCE : �f�B�[���[�̎�D��A���܂܂ꂽ������BJ�ɕی���������
    public void runInsurance() {
        Scanner sc = new Scanner(System.in);
        System.out.print("INSURANCE����`�b�v�̖�������͂��Ă�������\n> ");
        int tips = sc.nextInt();
    }

    //�|�[�J�[�Ŏ�D�����ւ���Ƃ��ɌĂ΂�郁�\�b�h
    public void handChengeOnPoker() {
        //��p�ɓ��͂��󂯕t����N���XInputScanner�������
        InputScanner scanner = new InputScanner("[12345]{1}|[OK]{2}|[ok]{2}|[Ok]{2}|[oK]{2}");
        //����ւ���J�[�h�̓Y�����i�[���郊�X�g
        List<Integer> number = new ArrayList<>();

        System.out.print("����ւ���J�[�h�ԍ�����͂��Ă�������(�ꖇ�Âԍ������ : �I����OK�����)\n> ");
        String tmp = scanner.scanLine();

        while (!tmp.equalsIgnoreCase("ok")) {
            number.add(Integer.parseInt(tmp));
            System.out.print("> ");
            tmp = scanner.scanLine();
        }
        System.out.println();

        //�d�����ꂽ�l�̍폜
        number = number.stream().distinct().collect(Collectors.toList());
        //�����ɑ��݂��Ȃ��Y������r�����鏈����}������

        //���͂��ꂽ�l�����D�̃J�[�h�����ւ���
        for (int i : number) {
            Card card = this.hand.get(i - 1);
            this.hand.set(i - 1, PokerManager.getStock().getCard());
            PokerManager.stock.add(card);
        }
        PokerManager.stock.shuffle();
    }
}
