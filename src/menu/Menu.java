package menu;

import util.Checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> items = new ArrayList<>();

    public void addMenu(int id, String title, Callback cb) {
        MenuItem item = new MenuItem(id, title, cb);
        items.add(item);
    }

    void resetMenu() {
        items.clear();
    }

    public void select() {
        try {
            int no = input();
            int id = items.get(no - 1).getID();
            items.get(no - 1).callMethod(id);
        } catch(IOException e) {
            System.err.println(e);
        }
    }

    private int input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int no = Checker.numberCheck(br.readLine(), items.size());
        System.out.println();
        return no;
    }

    public void show() {
        for (int i = 0; i < items.size(); i++ ) {
            System.out.println((i + 1) + ": " + items.get(i));
        }
    }
}
