package br.com.curriculopro.infra.controller;

import java.time.LocalDate;

public record EducationDto(
         Long id,
         String institution,
         String course,
         LocalDate start,
         LocalDate end) {}

