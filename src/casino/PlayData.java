package casino;

import data.Data;

import java.io.Serializable;

public class PlayData implements Serializable, Data {
    //インスタンス
    private static PlayData instance = new PlayData();

    private String name;              //プレイヤーの名前を格納する
    private long chips;               //プレイヤーの所有するチップ
    private long time;                //プレイヤーのプレイ時間

    private PlayData() {
        setClear();
    }

    public static PlayData getInstance() {
        return instance;
    }

    public static void setInstance(PlayData setData) {
        instance = setData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayData setClear() {
        this.name = "NULL";
        this.chips = 1000;
        this.time = 0;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public long getChips() {
        return this.chips;
    }

    public int getChips(int number) {
        if (this.chips < number) {
            return -1;
        }
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
