package com.scut.pojo;

public class Target {
    private Integer t_index;
    private String description;
    private Integer weight;
    private String d_name;
    private String position;

    public Target(Integer t_index, String description, Integer weight, String d_name, String position, Integer year, Integer semester) {
        this.t_index = t_index;
        this.description = description;
        this.weight = weight;
        this.d_name = d_name;
        this.position = position;
        this.year = year;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Target{" +
                "t_index=" + t_index +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", d_name='" + d_name + '\'' +
                ", position='" + position + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }

    public Target() {
    }

    public Integer getT_index() {
        return t_index;
    }

    public void setT_index(Integer t_index) {
        this.t_index = t_index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    private Integer year;
    private Integer semester;
}
