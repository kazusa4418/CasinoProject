package casino;

import playingcard.Card;
import playingcard.CardNumber;
import playingcard.CardSorterOnPoker;
import playingcard.CardStock;
import playingcard.Hand;
import util.Checker;
import util.ConsoleControl;
import util.Printer;
import util.Save;

import java.util.Scanner;

public class PokerManager implements Manager {
    private enum IsWinner {WIN, LOSE, DRAW}

    private static Scanner sc = new Scanner(System.in);
    private static Printer printer = new Printer();

    //カードのストック
    public static CardStock stock = new CardStock();
    //プレイヤー
    private Player pl = Player.getInstance();
    private ComPlayer com = ComPlayer.getInstance();

    public PokerManager() {
    }

    public void runGame() {
        printer.println("ポーカーを始めるよ！");
        //手札を配る
        distribute(pl.getHand());
        distribute(com.getHand());
        //参加費を徴収する
        printer.println("まずは参加費をもらうね。");
        pl.lostChips(10);
        printer.println("(所持チップが10枚減りました。)");
        //BETする
        do {
            System.out.print("何枚BETベットする？(所持チップ:" + pl.getChips() + "枚)\n> ");
            int bet = Integer.parseInt(Checker.stringCheck(sc.nextLine(), "[0-9]+"));
            pl.setBetChips(bet);

            if (pl.getBetChips() >= 0) {
                break;
            }
            System.out.println("おや？君はそんなにチップをもっていないよ。");
        } while(true);

        int betChips = pl.getBetChips();

        printer.println(betChips + "枚だね。\nそれじゃあ勝負を始めるよ！");
        System.out.println("あなたの手札:");
        for (int i = 0; i < pl.getHand().size(); i++) {
            System.out.println((i + 1) + ": " + pl.getHand().get(i));
        }
        //入れ替える手札を選ぶ
        pl.handChenge();
        //ｺﾝﾋﾟｭｰﾀが手札を入れ替える
        com.handChengeOnPoker();
        //プレイヤーとｺﾝﾋﾟｭｰﾀの手札を使って勝負する
        IsWinner result = battle(pl, com);

        switch (result) {
            case WIN:
                printer.println("あなたの勝ちです！");
                printer.println(betChips + "枚がBETされていたので" + (betChips * 2) + "枚の獲得です！");
                pl.getChips(betChips * 2);
                break;
            case LOSE:
                printer.println("あなたの負けです。");
                printer.println(betChips + "枚のチップを失いました。");
                break;
            case DRAW:
                printer.println("引き分けです。");
                printer.println("BETしたチップ" + betChips + "枚が戻ってきました。");
                pl.getChips(betChips);
                break;
        }
        //勝負がおわったので手札を山札にもどす
        retHand(pl.getHand());
        retHand(com.getHand());

        printer.println("所持チップ数が" + pl.getChips() + "になりました。");
        //所持チップが0枚になったらセーブデータを削除する
        if (pl.getChips() == 0) {
            printer.println("あれ？チップがなくなっちゃったの？");
            printer.println("残念だけど、この世界ではチップがないと遊べないんだ。");
            printer.println("セーブデータを消させてもらうね！");
            printer.println("(セーブデータが削除されます。)\n(まだ遊びたいならば再度セーブデータを作成してください。)");
            //Save.deleteFile();
            //Main.menu();
        }

        System.out.print("\n行動を選んでください\n\n1: もう一度\n2: タイトルへ戻る\n> ");
        int command = Checker.numberCheck(sc.nextLine(), 2);
        if (command == 1) {
            ConsoleControl.clearScreen();
            runGame();
        } else {
            System.out.println("タイトルへ戻ります");
            ConsoleControl.sleep(2000);
            ConsoleControl.clearScreen();
            //Title.title(pl, com);
        }
    }

    public static void distribute(Hand hand) {
        stock.shuffle();
        for (int i = 0; i < 5; i++) {
            hand.add(stock.takeCard());
        }
        hand.sort(new CardSorterOnPoker());
    }

    public void showRole(Result result) {
        switch (result.getStrong()) {
            case 0:
                System.out.println("ブタ");
                break;
            case 1:
                System.out.println("1ペア");
                break;
            case 2:
                System.out.println("2ペア");
                break;
            case 3:
                System.out.println("3カード");
                break;
            case 4:
                System.out.println("ストレート");
                break;
            case 5:
                System.out.println("フラッシュ");
                break;
            case 6:
                System.out.println("フルハウス");
                break;
            case 7:
                System.out.println("4カード");
                break;
            case 8:
                System.out.println("ストレートフラッシュ");
                break;
            case 9:
                System.out.println("ロイヤルストレートフラッシュ");
                break;
            case 10:
                System.out.println("5カード");
                break;
        }
    }

    //各プレイヤーの手札を用いて勝負する
    public IsWinner battle(Player pl, ComPlayer com) {
        Result plResult = Poker.handCheck(pl.getHand());
        Result comResult = Poker.handCheck(com.getHand());

        //お互いの手札をソートする
        pl.getHand().sort(new CardSorterOnPoker());
        com.getHand().sort(new CardSorterOnPoker());
        //お互いの手札を公開しあって、役を表示する
        System.out.println("******************************");
        System.out.println("SHOWDOWN!!\n");
        System.out.print("あなたの手札:");
        pl.showHand("WITH_INDEX");
        showRole(plResult);
        System.out.println("\n");
        System.out.print("ｺﾝﾋﾟｭｰﾀの手札:");
        com.showHand();
        showRole(comResult);
        System.out.println("******************************");
        //受け取ったResult型の変数を用いて勝敗判定を行う
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
                //ストレート同士のときに勝敗を下す
                if (firstStrong != IsWinner.DRAW) {
                    return firstStrong;
                }
                if (strongMark != IsWinner.DRAW) {
                    return strongMark;
                }
                return IsWinner.DRAW;
            case 5:
                //フラッシュ同士のときに勝敗を下す
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
                //ストレートフラッシュ同士のときに勝敗を下す
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
                //ロイヤルストレートフラッシュ同士のときに勝敗を下す
                //いかさまをするなお前ら!！！！！！！！！！！！！！！！！！！
                boolean plExist = false;
                boolean comExist = false;
                //プレイヤーの手札にAがあればtrue
                for (Card card : pl.getHand()) {
                    if (card.getNumber() == CardNumber.num1) {
                        plExist = true;
                    }
                }
                //ｺﾝﾋﾟｭｰﾀの手札にAがあればtrue
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
