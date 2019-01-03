package fr.bmarsaud.bingoifa.server.entity;

import java.sql.Time;

public class Box {
    private int id;
    private Sentence sentence;
    private int position;
    private boolean checked;
    private Time checkedTime;

    public Box(int id, Sentence sentence, int position, boolean checked, Time checkedTime) {
        this.id = id;
        this.sentence = sentence;
        this.position = position;
        this.checked = checked;
        this.checkedTime = checkedTime;
    }

    public Box() {
        id = -1;
        sentence = null;
        position = -1;
        checked = false;
        checkedTime = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Time getCheckedTime() {
        return checkedTime;
    }

    public void setCheckedTime(Time checkedTime) {
        this.checkedTime = checkedTime;
    }
}
