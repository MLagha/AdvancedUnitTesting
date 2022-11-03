package com.sparta.ml;

import java.time.LocalDate;

public class Spartan {
    private int id;                 //greater than 10 and less than 10000
    private String name;
    private String course;          //Java, C#, Data, DevOps, Cyber-Security, Business
    private LocalDate startDate;    //Cannot start a course in the past

    public Spartan(int id, String name, String course, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
