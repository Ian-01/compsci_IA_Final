package com.example.compsci_ia_final;

public class StudentAttendance extends Student {
    private boolean isPresent;
    private boolean isAbsent;
    private boolean isLate;
    private boolean isUndecided;

    public StudentAttendance(String name) {
        super(name);
        isPresent = false;
        isAbsent = false;
        isLate = false;
        isUndecided = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public boolean isAbsent() {
        return isAbsent;
    }

    public boolean isLate() {
        return isLate;
    }

    public boolean isUndecided() {
        return isUndecided;
    }

    public void setPresent() {
        isPresent = true;
        isAbsent = false;
        isLate = false;
        isUndecided = false;
    }

    public void setAbsent() {
        isAbsent = true;
        isPresent = false;
        isLate = false;
        isUndecided = false;
    }

    public void setLate() {
        isLate = true;
        isPresent = false;
        isAbsent = false;
        isUndecided = false;
    }

    public void setUndecided() {
        isUndecided = true;
        isPresent = false;
        isAbsent = false;
        isLate = false;
    }
}