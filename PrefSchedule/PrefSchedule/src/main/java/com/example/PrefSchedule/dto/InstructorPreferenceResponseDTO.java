package com.example.PrefSchedule.dto;

public class InstructorPreferenceResponseDTO {

    private Long id;
    private Long optionalCourseId;
    private String compulsoryAbbr;
    private double percentage;

    public InstructorPreferenceResponseDTO(Long id, Long courseId, String abbr, double percentage) {
        this.id = id;
        this.optionalCourseId = courseId;
        this.compulsoryAbbr = abbr;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOptionalCourseId() {
        return optionalCourseId;
    }

    public void setOptionalCourseId(Long optionalCourseId) {
        this.optionalCourseId = optionalCourseId;
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

