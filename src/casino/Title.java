package casino;

import util.ConsoleControl;
import util.Save;
import util.Checker;
import java.io.File;
import java.util.Scanner;

public class Title {
    private static Scanner sc = new Scanner(System.in);

    public static void title(Player pl, ComPlayer com) {
        System.out.println("�Ȃ�̃Q�[���ŗV�т܂����H\n");
        System.out.print("1:�u���b�N�W���b�N\n2:�|�[�J�[\n3:�v���C�f�[�^���Q��\n4:�^�C�g���֖߂�\n> ");
        int command = Integer.parseInt(Checker.stringCheck(sc.nextLine(), "[1234]{1}"));
        if (command == 1) {
            BlackJackManager jack = new BlackJackManager(pl, com);
            jack.runGame();
            title(pl, com);
        } else if (command == 2) {
            PokerManager poker = new PokerManager(pl, com);
            poker.runGame();
            title(pl, com);
        } else if (command == 3) {
            pl.showPlayData();
            title(pl, com);
        } else {
            //�^�C�g���֖߂�O�ɃZ�[�u�������s��
            if (pl.getPlayData().isGUEST()) {
                //GUEST�ł���΃Z�[�u���s�킸�C���X�^���X��j������
                pl = null;
                System.out.println("�^�C�g���֖߂�܂��B");

                ConsoleControl.sleep(2000);
                ConsoleControl.clearScreen();
                Main.menu();
            }
            System.out.println("�Z�[�u���ă^�C�g���֖߂�܂��B");
            File file = Save.getFile(Main.fileNumber);
            PlayData myData = pl.getPlayData();
            Save.writeFile(file, myData);

            ConsoleControl.sleep(2000);
            ConsoleControl.clearScreen();

            Main.menu();
        }
    }
}
