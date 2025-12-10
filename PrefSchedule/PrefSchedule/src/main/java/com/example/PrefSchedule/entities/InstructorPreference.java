package com.example.PrefSchedule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_preferences")
public class InstructorPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "optional_course_id")
    private Course optionalCourse;

    @Column(nullable = false)
    private String compulsoryAbbr;

    @Column(nullable = false)
    private double percentage;

    public InstructorPreference() {}

    public InstructorPreference(Course optionalCourse, String compulsoryAbbr, double percentage) {
        this.optionalCourse = optionalCourse;
        this.compulsoryAbbr = compulsoryAbbr;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getOptionalCourse() {
        return optionalCourse;
    }

    public void setOptionalCourse(Course optionalCourse) {
        this.optionalCourse = optionalCourse;
    }

    public String getCompulsoryAbbr() {
        return compulsoryAbbr;
    }

    public void setCompulsoryAbbr(String compulsoryAbbr) {
        this.compulsoryAbbr = compulsoryAbbr;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
