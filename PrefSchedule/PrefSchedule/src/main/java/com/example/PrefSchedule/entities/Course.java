package com.example.PrefSchedule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String code;

    private String abbr;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    private Pack pack;

    @Column(name = "group_count")
    private Integer groupCount;

    private String description;

    public Course() {}

    public Course(Long id, String type, String code, String abbr, String name,
                  Instructor instructor, Pack pack, Integer groupCount, String description) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.abbr = abbr;
        this.name = name;
        this.instructor = instructor;
        this.pack = pack;
        this.groupCount = groupCount;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getAbbr() { return abbr; }
    public void setAbbr(String abbr) { this.abbr = abbr; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    public Pack getPack() { return pack; }
    public void setPack(Pack pack) { this.pack = pack; }

    public Integer getGroupCount() { return groupCount; }
    public void setGroupCount(Integer groupCount) { this.groupCount = groupCount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", abbr='" + abbr + '\'' +
                ", name='" + name + '\'' +
                ", instructorId=" + instructor +
                ", groupCount=" + groupCount +
                ", description='" + description + '\'' +
                '}';
    }
}
