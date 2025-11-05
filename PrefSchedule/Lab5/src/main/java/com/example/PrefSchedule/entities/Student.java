package com.example.PrefSchedule.entities;
import com.example.PrefSchedule.AbstractPerson;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends AbstractPerson {

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private int year;

    public Student() {}

    public Student(Long id, String code, String name, String email, int year) {
        super(id, name, email);
        this.code = code;
        this.year = year;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", year=" + year +
                '}';
    }
}
