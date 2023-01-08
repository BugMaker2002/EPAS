package com.scut.pojo;

public class MutualData {
    private int number;
    private int assess_ID;
    private int be_assess_ID;
    private String relationship;
    private int year;
    private int semester;
    private String result;

    public MutualData() {
    }

    public MutualData(int number, int assess_ID, int be_assess_ID, String relationship, int year, int semester, String result) {
        this.number = number;
        this.assess_ID = assess_ID;
        this.be_assess_ID = be_assess_ID;
        this.relationship = relationship;
        this.year = year;
        this.semester = semester;
        this.result = result;
    }

    @Override
    public String toString() {
        return "MutualData{" +
                "number=" + number +
                ", assess_ID=" + assess_ID +
                ", be_assess_ID=" + be_assess_ID +
                ", relationship='" + relationship + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                ", result='" + result + '\'' +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAssess_ID() {
        return assess_ID;
    }

    public void setAssess_ID(int assess_ID) {
        this.assess_ID = assess_ID;
    }

    public int getBe_assess_ID() {
        return be_assess_ID;
    }

    public void setBe_assess_ID(int be_assess_ID) {
        this.be_assess_ID = be_assess_ID;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
