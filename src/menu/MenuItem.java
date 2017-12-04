package menu;

public class MenuItem {
  private int id;
  private String title;
  private Callback cb;

  public MenuItem(int id, String title, Callback cb) {
      this.id = id;
      this.title = title;
      this.cb = cb;
  }

  public String toString() {
    return this.title;
  }

  public void callMethod(int id) {
    cb.callback(id);
  }

  public int getID() {
      return this.id;
  }
}
