package poker;

import card.Card;
import card.CardStock;
import card.element.CardNumber;
import card.sorter.CardSorterOnPoker;
import system.tools.Printer;
import player.Player;
import player.ComPlayer;
import player.hand.Hand;
import system.checker.Checker;
import title.Title;

import java.util.Scanner;

public class PokerManager {
    private enum IsWinner {WIN, LOSE, DRAW}

    private static Scanner sc = new Scanner(System.in);
    private static Printer printer = new Printer();

    //�J�[�h�̃X�^�b�N
    public static CardStock stock = new CardStock();
    //�v���C���[
    private static Player pl;
    private static ComPlayer com;
    //BET�����`�b�v
    public static int plBetChips = 0;

    public PokerManager(Player pl, ComPlayer com) {
        this.pl = pl;
        this.com = com;
    }

    public void runGame() {
        System.out.println("�|�[�J�[���n�߂��I\n��D��z��ˁI\n");
        //��D��z��
        distribute(pl.getHand());
        distribute(com.getHand());
        //�����̎�D��\������
        System.out.println("���Ȃ��̎�D:");
        for (int i = 0; i < pl.getHand().size(); i++) {
            System.out.println((i + 1) + ": " + pl.getHand().get(i));
        }
        //�Q��������炤
        System.out.println("�܂��͎Q����Ń`�b�v��10�����炤�ˁB");
        plBetChips = pl.betChips(10);
        System.out.println("(�`�b�v��10������܂����B)");
        //BET����
        System.out.print("���ꂶ�ႠBET�Ɉڂ��B\n����BET����H\n> ");
        plBetChips = pl.betChips(Integer.parseInt(sc.nextLine()));

        System.out.println(plBetChips + "�����ˁB\n���ꂶ�Ⴀ�������n�߂��I\n");
        System.out.println("���Ȃ��̎�D:");
        for (int i = 0; i < pl.getHand().size(); i++) {
            System.out.println((i + 1) + ": " + pl.getHand().get(i));
        }
        //����ւ����D��I��
        pl.handChengeOnPoker();
        //�R���s���[�^����D�����ւ���
        com.handChengeOnPoker();
        //�v���C���[�̎�D�ƃR���s���[�^�̎�D���g���ď�������
        IsWinner result = battle(pl, com);

        switch (result) {
            case WIN:
                printer.println("���Ȃ��̏����ł��I");
                printer.println(plBetChips + "����BET������Ă����̂�" + (plBetChips * 2) + "���̊l���ł��I");
                pl.getChips(plBetChips * 2);
                break;
            case LOSE:
                printer.println("���Ȃ��̕����ł��B");
                printer.println(plBetChips + "���̃`�b�v�������܂����B");
                pl.lostChips(plBetChips);
                break;
            case DRAW:
                printer.println("���������ł��B");
                printer.println("BET�����`�b�v" + plBetChips + "�����߂��Ă��܂����B");
                pl.getChips(plBetChips);
                break;
        }
        printer.println("�����`�b�v����" + pl.getChips() + "�ɂȂ�܂����B");
        //�������I������̂ł��ꂼ��̎�D���R�D�ɖ߂�
        retHand(pl.getHand());
        retHand(com.getHand());

        System.out.print("\n�s����I��ł�������\n\n1: ������x\n2: �^�C�g���֖߂�\n> ");
        int command = Checker.numberCheck(sc.nextLine(), 2);
        if (command == 1) {
            runGame();
        } else {
            Title.start(pl, com);
        }
    }

    public static void distribute(Hand hand) {
        stock.shuffle();
        for (int i = 0; i < 5; i++) {
            hand.add(stock.getCard());
        }
        hand.sortCard(new CardSorterOnPoker());
    }

    public void showRole(Result result) {
        switch (result.getStrong()) {
            case 0:
                System.out.println("�u�^");
                break;
            case 1:
                System.out.println("1�y�A");
                break;
            case 2:
                System.out.println("2�y�A");
                break;
            case 3:
                System.out.println("3�J�[�h");
                break;
            case 4:
                System.out.println("�X�g���[�g");
                break;
            case 5:
                System.out.println("�t���b�V��");
                break;
            case 6:
                System.out.println("�t���n�E�X");
                break;
            case 7:
                System.out.println("4�J�[�h");
                break;
            case 8:
                System.out.println("�X�g���[�g�t���b�V��");
                break;
            case 9:
                System.out.println("���C�����X�g���[�g�t���b�V��");
                break;
            case 10:
                System.out.println("5�J�[�h");
                break;
        }
    }

    //�e�v���C���[�̎�D��p���ď�������
    public IsWinner battle(Player pl, ComPlayer com) {
        Result plResult = Poker.handCheck(pl.getHand());
        Result comResult = Poker.handCheck(com.getHand());

        //���݂��̎�D���\�[�g����
        pl.getHand().sortCard(new CardSorterOnPoker());
        com.getHand().sortCard(new CardSorterOnPoker());
        //���݂��̎�D�����J�������āA����\������
        System.out.println("******************************");
        System.out.println("SHOWDOWN!!\n");
        System.out.print("���Ȃ��̎�D:");
        pl.showHand();
        showRole(plResult);
        System.out.println("\n");
        System.out.print("���߭���̎�D:");
        com.showHand();
        showRole(comResult);
        System.out.println("******************************");
        //�󂯎����Result�^�̕ϐ���p���ď��s������s��
        IsWinner score = checkScore(plResult.getStrong(), comResult.getStrong());
        IsWinner firstStrong = checkFirstStrong(plResult.getFirstStrong().ordinal(),
                comResult.getFirstStrong().ordinal());
        IsWinner secondStrong = checkSecondStrong(plResult.getSecondStrong().ordinal(),
                comResult.getSecondStrong().ordinal());
        IsWinner strongMark = checkStrongMark(plResult.getStrongMark().ordinal(),
                comResult.getStrongMark().ordinal());
        IsWinner jokerNumber = checkJokerNumber(plResult.getJokerNumber(), comResult.getJokerNumber());

        if (score != IsWinner.DRAW) {
            return score;
        }
        switch (plResult.getStrong()) {
            case 4:
                //�X�g���[�g���m�̂Ƃ��ɏ��s������
                if (firstStrong != IsWinner.DRAW) {
                    return firstStrong;
                }
                if (strongMark != IsWinner.DRAW) {
                    return strongMark;
                }
                return IsWinner.DRAW;
            case 5:
                //�t���b�V�����m�̂Ƃ��ɏ��s������
                if (strongMark != IsWinner.DRAW) {
                    return strongMark;
                }
                if (jokerNumber != IsWinner.DRAW) {
                    return jokerNumber;
                }
                if (firstStrong != IsWinner.DRAW) {
                    return firstStrong;
                }
                return IsWinner.DRAW;
            case 8:
                //�X�g���[�g�t���b�V�����m�̂Ƃ��ɏ��s������
                if (strongMark != IsWinner.DRAW) {
                    return strongMark;
                }
                if (firstStrong != IsWinner.DRAW) {
                    return firstStrong;
                }
                if (jokerNumber != IsWinner.DRAW) {
                    return jokerNumber;
                }
                return IsWinner.DRAW;
            case 9:
                //���C�����X�g���[�g�t���b�V�����m�̂Ƃ��ɏ��s������
                //�������܂�����Ȃ��܂���I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I
                boolean plExist = false;
                boolean comExist = false;
                //�v���C���[�̎�D��A�������true
                for (Card card : pl.getHand()) {
                    if (card.getNumber() == CardNumber.num1) {
                        plExist = true;
                    }
                }
                //�R���s���[�^�̎�D��A�������true
                for (Card card : com.getHand()) {
                    if (card.getNumber() == CardNumber.num1) {
                        comExist = true;
                    }
                }
                if (strongMark != IsWinner.DRAW) {
                    return strongMark;
                }
                if (plExist == true && comExist == false) {
                    return IsWinner.WIN;
                }
                if (plExist == false && comExist == true) {
                    return IsWinner.LOSE;
                }
                if (jokerNumber != IsWinner.DRAW) {
                    return jokerNumber;
                }
            default:
                if (firstStrong != IsWinner.DRAW) {
                    return firstStrong;
                }
                if (secondStrong != IsWinner.DRAW) {
                    return secondStrong;
                }
                if (jokerNumber != IsWinner.DRAW) {
                    return jokerNumber;
                }
                if (strongMark != IsWinner.DRAW) {
                    return strongMark;
                }
        }
        return IsWinner.DRAW;
    }

    public IsWinner checkScore(int plScore, int comScore) {
        if (plScore > comScore) {
            return IsWinner.WIN;
        } else if (plScore < comScore) {
            return IsWinner.LOSE;
        } else {
            return IsWinner.DRAW;
        }
    }

    public IsWinner checkFirstStrong(int plFirstStrong, int comFirstStrong) {
        if (plFirstStrong > comFirstStrong) {
            return IsWinner.WIN;
        } else if (plFirstStrong < comFirstStrong) {
            return IsWinner.LOSE;
        } else {
            return IsWinner.DRAW;
        }
    }

    public IsWinner checkSecondStrong(int plSecondStrong, int comSecondStrong) {
        if (plSecondStrong > comSecondStrong) {
            return IsWinner.WIN;
        } else if (plSecondStrong < comSecondStrong) {
            return IsWinner.LOSE;
        } else {
            return IsWinner.DRAW;
        }
    }

    public IsWinner checkStrongMark(int plStrongMark, int comStrongMark) {
        if (plStrongMark > comStrongMark) {
            return IsWinner.WIN;
        } else if (plStrongMark < comStrongMark) {
            return IsWinner.LOSE;
        } else {
            return IsWinner.DRAW;
        }
    }

    public IsWinner checkJokerNumber(int plJokerNumber, int comJokerNumber) {
        if (plJokerNumber > comJokerNumber) {
            return IsWinner.LOSE;
        } else if (plJokerNumber < comJokerNumber) {
            return IsWinner.WIN;
        } else {
            return IsWinner.DRAW;
        }
    }

    public void retHand(Hand hand) {
        for (Card card : hand) {
            stock.add(card);
        }
        hand.clear();
    }

    public static CardStock getStock() {
        return stock;
    }

}
