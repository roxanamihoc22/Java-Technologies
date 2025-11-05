package com.example.PrefSchedule.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "packs")
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;

    private int semester;

    private String name;

    @OneToMany(mappedBy = "pack")
    private List<Course> courses = new ArrayList<>();

    public Pack() {}

    public Pack(Long id, int year, int semester, String name) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    @Override
    public String toString() {
        return "Pack{" +
                "id=" + id +
                ", year=" + year +
                ", semester=" + semester +
                ", name='" + name + '\'' +
                '}';
    }
}
