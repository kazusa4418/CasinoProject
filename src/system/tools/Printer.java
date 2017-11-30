package system.tools;

import java.util.Scanner;

public class Printer {
    private Scanner sc = new Scanner(System.in);

    public void print(Object o) {
        System.out.print(o);
        sc.nextLine();
    }

    public void println(Object o) {
        System.out.println(o);
        sc.nextLine();
    }
}
