package com.example.sheduleforictis.models;

public class Couple {
    private String timeStart;
    private String timeEnd;
    private int numOfCouple;
    private String kindOfConducting;
    private String nameOfCouple;
    private String audience;
    private String professor;
    private Boolean isOnline;

    public Couple(String timeStart,
                  String timeEnd,
                  int numOfCouple,
                  String kindOfConducting,
                  String nameOfCouple,
                  String audience,
                  String professor
    ) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.numOfCouple = numOfCouple;
        this.kindOfConducting = kindOfConducting;
        this.nameOfCouple = nameOfCouple;
        this.audience = audience;
        this.professor = professor;
        setOnline();
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public int getNumOfCouple() {
        return numOfCouple;
    }

    public String getKindOfConducting() {
        return kindOfConducting;
    }

    public String getNameOfCouple() {
        return nameOfCouple;
    }

    public String getAudience() {
        return audience;
    }

    public String getProfessor() {
        return professor;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    private void setOnline() {
        this.isOnline = kindOfConducting.equals("LMS") || kindOfConducting.equals("LMS-1");
    }
}
