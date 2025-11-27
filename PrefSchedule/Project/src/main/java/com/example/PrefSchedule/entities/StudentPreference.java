package com.example.PrefSchedule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "preferences")
public class StudentPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pack_id")
    private Pack pack;

    @Column(nullable = false)
    private int rank;


    public StudentPreference() {}

    public StudentPreference(Long id, Student student, Course course, Pack pack, int rank) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.pack = pack;
        this.rank = rank;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public Pack getPack() { return pack; }
    public void setPack(Pack pack) { this.pack = pack; }
    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }
}

