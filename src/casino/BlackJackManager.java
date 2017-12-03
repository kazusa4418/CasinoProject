package casino;

import playingcard.*;
import util.Checker;
import util.InputScanner;
import util.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BlackJackManager {
    private static Printer printer = new Printer();
    private static Scanner sc = new Scanner(System.in);
    private static Map<CardNumber, Integer> numbers = new HashMap<>();
    public static CardStock stock = new CardStock(0);
    public static Player pl;
    public static ComPlayer com;

    public BlackJackManager() {
        //�}�b�v��CardNumber�ƃJ�[�h�̐������֘A�t����
        for (CardNumber element : CardNumber.values()) {
            if (element == CardNumber.JOKER) {
                continue;
            }
            if (element == CardNumber.num1) {
                numbers.put(element, 1);
                continue;
            }
            if (element.ordinal() < 9) {
                numbers.put(element, element.ordinal() + 2);
            } else {
                numbers.put(element, 10);
            }
        }
    }

    public BlackJackManager(Player pl, ComPlayer com) {
        this();
        this.pl = pl;
        this.com = com;
    }

    public void runGame() {
        printer.println("�u���b�N�W���b�N���n�߂��I");
        //BET����
        do {
            System.out.print("����BET����H(�����`�b�v��:" + pl.getChips() + "��)\n> ");
            int bet = Integer.parseInt(Checker.stringCheck(sc.nextLine(), "[0-9]+"));
            pl.setBetChips(bet);

            if (pl.getBetChips() >= 0) {
                break;
            }
            printer.println("����H�N�͂���ȂɃ`�b�v�������Ă��Ȃ���B");
        } while(true);
        int betChips = pl.getBetChips();

        printer.println(betChips + "�����ˁB\n���ꂶ�Ⴀ�������n�߂��I");
        //��D��z��
        distribute(pl.getHand());
        distribute(com.getHand());
        //��������܂ōs���s����I��������
        runAction();
        //�����A����
    }

    public static void runAction() {
        //�e�v���C���[�̎�D��p���Ė��̋����𒲂ׂ�
        int plStrong = BlackJack.handCheck(pl.getHand());
        int comStrong = BlackJack.handCheck(com.getHand());
        //�v���C���[�̎�D�����J����
        writeLine();
        System.out.println("���Ȃ��̎�D:");
        pl.showHand();
        System.out.println("���Ȃ��̋���: " + plStrong + "\n\n");
        //�f�B�[���[(�R���s���[�^)�̎�D�����J����
        System.out.println("�f�B�[���[�̎�D:");
        com.showHand("runBlackJack");
        writeLine();
        printer.pleaseEnter();

        //�s���s����I��������
        String action = act(BlackJack.canSplit(pl.getHand()), BlackJack.canInsurance(com.getHand()));
        //action�Ɋi�[����Ă��镶���񂩂�s�����������߂�
        switch (action) {
            case "HIT":
                pl.runHit();
                runAction();
                return;
            case "STAND":
                System.out.println("�����A�����I");
                return;
            case "DOUBLE":
                pl.runDouble();
                runAction();
                return;
            case "SURRENDER":
                System.out.println("DEBUG:�������~��܂���");
                runAction();
                return;
            case "SPLIT":
                System.out.println("DEBUG:SPLIT�����s����܂���");
                runAction();
                return;
            case "INSURANCE":
                System.out.println("DEBUG:INSURANCE�����s����܂���");
                runAction();
                return;
            case "NULL":
                System.err.println("�G���[:�����I��");
                System.exit(1);
        }
    }

    public static Map<CardNumber, Integer> getNumberMap() {
        return numbers;
    }

    public static String act(boolean canSplit, boolean canInsurance) {
        List<String> action = new ArrayList<>();
        action.add(":HIT (�J�[�h���ꖇ����)");
        action.add(":STAND (��������)");
        action.add(":DOUBLE (�|�������{�ɂ��ăJ�[�h���ꖇ����)");
        action.add(":SURRENDER (�|�����𔼕��̂Ăď������~���)");

        if (canSplit) {
            action.add(":SPLIT����");
        }
        if (canInsurance) {
            action.add(":INSURANCE����");
        }

        System.out.println("�s����I��ł�������\n");
        for (int i = 0; i < action.size(); i++) {
            System.out.println((i + 1) + action.get(i));
        }
        //���X�g�̃T�C�Y������͂��󂯕t���镶��������肷��
        String text = "";
        for (int i = 0; i < action.size(); i++) {
            text += (i + 1);
        }
        String format = "[" + text + "]{1}";
        InputScanner scanner = new InputScanner();

        System.out.println("\n(��������͂��čs����I��)");
        System.out.print("> ");
        int command = scanner.scanInt(format);

        switch (command) {
            case 1:
                return "HIT";
            case 2:
                return "STAND";
            case 3:
                return "DOUBLE";
            case 4:
                return "SURRENDER";
            case 5:
                if (canSplit) {
                    return "SPLIT";
                }
                return "INSURANCE";
            case 6:
                return "INSURANCE";
        }
        System.out.println("�s�����}�b�`���O�𓦂��܂���");
        return "NULL";
    }

    public static void writeLine() {
        System.out.println("******************************************");
    }

    //������D��z�邽�߂ɗp������static���\�b�h
    public static void distribute(Hand hand) {
        stock.shuffle();
        hand.add(stock.takeCard());
        hand.add(stock.takeCard());
        hand.sort(new CardSorter());
    }
}
