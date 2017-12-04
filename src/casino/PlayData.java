package casino;

import util.Data;

import java.io.Serializable;

public class PlayData implements Serializable, Data {
    private String name;              //�v���C���[�̖��O���i�[����
    private long chips;               //�v���C���[�̏��L����`�b�v
    private long time;                //�v���C���[�̃v���C����

    PlayData() {
        this.name = "NULL";
        this.chips = 1000;
        this.time = 0;
    }

    public void setName(String name) {
        this.name = name;
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
