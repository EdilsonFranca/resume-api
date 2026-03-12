package br.com.curriculopro.domain.entities;

import br.com.curriculopro.domain.enums.PROVIDER;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private PROVIDER provider; // LOCAL ou GOOGLE
    private Set<Resume> resumes = new HashSet<>();

    public User(String name, String email, String role, PROVIDER provider) {
        this.name     = name;
        this.email    = email;
        this.role     = role;
        this.provider = provider;
    }

    public User(Long id, String role , String name , String email, String password, PROVIDER provider ) {
        this.id       = id;
        this.role     = role;
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public PROVIDER getProvider() {
        return provider;
    }

    public Set<Resume> getResumes() {
        return resumes;
    }
}
