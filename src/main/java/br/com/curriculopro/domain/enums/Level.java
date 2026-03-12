package br.com.curriculopro.domain.enums;

public enum Level {

    BASIC("Básico"),
    INTERMEDIATE("Intermediário"),
    ADVANCED("Avançado"),
    FLUENT("Fluente");

    private final String description;

    Level(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}



