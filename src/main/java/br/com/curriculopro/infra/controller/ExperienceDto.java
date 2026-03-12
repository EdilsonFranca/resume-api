package br.com.curriculopro.infra.controller;

import br.com.curriculopro.domain.enums.State;

import java.time.LocalDate;

public record ExperienceDto(
        Long id,
        String nameCompany,
        LocalDate start,
        LocalDate end,
        String position,
        String description,
        String city,
        State state) {}
