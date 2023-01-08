package com.scut.pojo;

public class Result {
    private int number;
    private int ID;
    private int year;
    private int semester;

    public int getNumber() {
        return number;
    }

    public Result(int number, int ID, int year, int semester, String grade, double proximity) {
        this.number = number;
        this.ID = ID;
        this.year = year;
        this.semester = semester;
        this.grade = grade;
        this.proximity = proximity;
    }

    @Override
    public String toString() {
        return "Result{" +
                "number=" + number +
                ", ID=" + ID +
                ", year=" + year +
                ", semester=" + semester +
                ", grade='" + grade + '\'' +
                ", proximity=" + proximity +
                '}';
    }

    public Result() {
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getProximity() {
        return proximity;
    }

    public void setProximity(double proximity) {
        this.proximity = proximity;
    }

    private String grade;
    private double proximity;
}
