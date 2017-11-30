package player.data;

import java.io.Serializable;

public class PlayData implements Serializable {
    private String name;    //プレイヤーの名前を格納する
    private long chips;     //プレイヤーの所有するチップ
    private long time;

    public PlayData() {
        this("GUEST");
    }

    public PlayData(String name) {
        this.name = name;
        this.chips = 1000;
        this.time = 0;
    }

    public String getName() {
        return this.name;
    }

    public long getChips() {
        return this.chips;
    }

    public int getChips(int number) {
        this.chips -= number;
        return number;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void plusChips(int number) {
        this.chips += number;
    }

    public long getPlayTime() {
        return this.time;
    }

    public void plusPlayTime(long time) {
        this.time += time;
    }

}
