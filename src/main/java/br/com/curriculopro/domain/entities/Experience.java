package br.com.curriculopro.domain.entities;

import br.com.curriculopro.domain.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class Experience {
    private Long id;
    private String nameCompany;
    private LocalDate start;
    private LocalDate end;
    private String position;
    private String description;
    private String city;

    @Enumerated(EnumType.STRING)
    private State state;

    public Experience(Long id, String nameCompany, LocalDate start, LocalDate end, String position, String description, String city, State state) {
        this.id = id;
        this.nameCompany = nameCompany;
        this.start = start;
        this.end = end;
        this.position = position;
        this.description = description;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

}
