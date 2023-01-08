package com.scut.pojo;

public class SelfAssess {
    private int ID;
    private String description;
    private int year;

    @Override
    public String toString() {
        return "SelfAssess{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }

    public SelfAssess() {
    }

    public int getID() {
        return ID;
    }

    public SelfAssess(int ID, String description, int year, int semester) {
        this.ID = ID;
        this.description = description;
        this.year = year;
        this.semester = semester;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    private int semester;
}
