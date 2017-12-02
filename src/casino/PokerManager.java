package casino;

import playingcard.Card;
import playingcard.CardNumber;
import playingcard.CardSorterOnPoker;
import playingcard.CardStock;
import playingcard.Hand;
import util.Checker;
import util.Printer;
import util.Save;

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

    public PokerManager(Player pl, ComPlayer com) {
        this.pl = pl;
        this.com = com;
    }

    public void runGame() {
        printer.println("�|�[�J�[���n�߂��I");
        //��D��z��
        distribute(pl.getHand());
        distribute(com.getHand());
        //�Q��������炤
        printer.println("�܂��͎Q����Ń`�b�v��10�����炤�ˁB");
        pl.lostChips(10);
        printer.println("(�`�b�v��10������܂����B)");
        //BET����
        do {
            System.out.print("����BET����H(�����`�b�v��:" + pl.getChips() + "��)\n> ");
            int bet = Integer.parseInt(Checker.stringCheck(sc.nextLine(), "[0-9]+"));
            pl.setBetChips(bet);

            if (pl.getBetChips() >= 0) {
                break;
            }
            System.out.println("����H�N�͂���ȂɃ`�b�v�������Ă��Ȃ���B");
        } while(true);

        int betChips = pl.getBetChips();

        printer.println(betChips + "�����ˁB\n���ꂶ�Ⴀ�������n�߂��I");
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
                printer.println(betChips + "����BET������Ă����̂�" + (betChips * 2) + "���̊l���ł��I");
                pl.getChips(betChips * 2);
                break;
            case LOSE:
                printer.println("���Ȃ��̕����ł��B");
                printer.println(betChips + "���̃`�b�v�������܂����B");
                pl.lostChips(betChips);
                break;
            case DRAW:
                printer.println("���������ł��B");
                printer.println("BET�����`�b�v" + betChips + "�����߂��Ă��܂����B");
                pl.getChips(betChips);
                break;
        }
        //�������I������̂ł��ꂼ��̎�D���R�D�ɖ߂�
        retHand(pl.getHand());
        retHand(com.getHand());

        printer.println("�����`�b�v����" + pl.getChips() + "�ɂȂ�܂����B");
        //�����`�b�v��0���ɂȂ��Ă��܂�����Z�[�u�f�[�^���폜����
        if (pl.getChips() == 0) {
            printer.println("����H�`�b�v���Ȃ��Ȃ���������́H");
            printer.println("�c�O�����ǂ��̐��E�ł̓`�b�v���Ȃ��ƗV�ׂȂ���");
            printer.println("�Z�[�u�f�[�^���������Ă��炤�ˁI�I�I");
            printer.println("(�Z�[�u�f�[�^���폜����܂��B)\n(�܂��V�т����Ȃ�ēx�Z�[�u�f�[�^���쐬���Ă�������)");
            if (pl.getPlayData().isGUEST()) {
                pl = null;
                Main.menu();
            }
            Save.deleteFile(Main.fileNumber);
            Main.menu();
        }

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
        hand.sort(new CardSorterOnPoker());
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
        pl.getHand().sort(new CardSorterOnPoker());
        com.getHand().sort(new CardSorterOnPoker());
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
                if (plExist && !comExist) {
                    return IsWinner.WIN;
                }
                if (!plExist && comExist) {
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
        stock.addAll(hand);
        hand.clear();
    }

    public static CardStock getStock() {
        return stock;
    }

}
