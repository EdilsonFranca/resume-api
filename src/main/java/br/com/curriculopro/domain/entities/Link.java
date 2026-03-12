package br.com.curriculopro.domain.entities;

public class Link {
    private Long id;
    private String description;

    public Link(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
