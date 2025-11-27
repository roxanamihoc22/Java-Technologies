package com.example.PrefSchedule.dto;

import jakarta.validation.constraints.*;

public class PreferenceRequestDTO {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Pack ID is required")
    private Long packId;

    @Min(value = 1, message = "Rank must be at least 1")
    private int rank;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public Long getPackId() { return packId; }
    public void setPackId(Long packId) { this.packId = packId; }
    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }
}

