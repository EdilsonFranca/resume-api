package br.com.curriculopro.domain.entities;

import br.com.curriculopro.domain.enums.Level;

public class Language {
    private Long id;
    private String name;
    private Level level;

    public Language(Long id, String name, Level level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }
}
