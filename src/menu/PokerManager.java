package menu;

public class PokerManager implements Callback {
  public static void main(String[] args) {
    PokerManager pm = new PokerManager();
    pm.init();
  }

  public void callback(int no) {
    switch(no) {
      case 1:
        System.out.println("1を選択");
        break;
      case 2:
        System.out.println("2を選択");
        break;
      default:
        break;
    }
  }

  void init() {
    Menu menu = new Menu();
    menu.addMenu(1, "最初", this);
    menu.addMenu(2, "次へ", this);

    while(true)
      menu.show();
  }
}
