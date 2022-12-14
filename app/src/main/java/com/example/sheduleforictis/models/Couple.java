package com.example.sheduleforictis.models;

public class Couple {
    private String timeStart;
    private String timeEnd;
    private int numOfCouple;
    private String nameOfCouple;
    private String numOfCoupleStr;
    private String audience;
    private String professor;
    private Boolean isOnline;

    public Couple(String timeStart,
                  String timeEnd,
                  int numOfCouple,
                  String nameOfCouple,
                  String audience,
                  String professor
    ) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.numOfCouple = numOfCouple;
        this.nameOfCouple = nameOfCouple;
        this.professor = professor;
        setAudience(audience);
        setOnline(audience);
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

    public String getNumOfCoupleStr() {
        return numOfCoupleStr;
    }

    public void setNumOfCoupleStr(String numOfCoupleStr) {
        this.numOfCoupleStr = numOfCoupleStr;
    }

    private void setOnline(String audience) {
        this.isOnline = audience.equals("LMS");
        System.out.println("hello");
    }

    private void setAudience(String audience) {
        if (audience.equals("LMS")) this.audience = "Онлайн";
        else this.audience = audience;
        System.out.println("hello");
    }
}
