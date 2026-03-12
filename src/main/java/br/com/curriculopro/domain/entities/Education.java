package br.com.curriculopro.domain.entities;

import java.time.LocalDate;

public class Education {
    private Long id;
    private String institution;
    private String course;
    private LocalDate start;
    private LocalDate end;

    public Education(Long id, String institution, String course, LocalDate start, LocalDate end) {
        this.id = id;
        this.institution = institution;
        this.course = course;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public String getInstitution() {
        return institution;
    }

    public String getCourse() {
        return course;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
