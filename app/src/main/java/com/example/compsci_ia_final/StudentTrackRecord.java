package com.example.compsci_ia_final;

public class StudentTrackRecord extends Student {

    private int present;
    private int absent;
    private int late;
    private int streaks;
    private String level;

    public StudentTrackRecord(String name, int present, int absent, int late, int streaks, String level) {
        super(name);
        this.present = present;
        this.absent = absent;
        this.late = late;
        this.streaks = streaks;
        this.level = level;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getStreaks() {
        return streaks;
    }

    public void setStreaks(int streaks) {
        this.streaks = streaks;
    }

    public String getLevel() {
        return level;
    }


    public void setLevel(String level) {
        this.level = level;
    }
}