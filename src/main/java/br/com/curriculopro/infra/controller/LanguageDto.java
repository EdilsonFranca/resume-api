package br.com.curriculopro.infra.controller;

import br.com.curriculopro.domain.enums.Level;

public record LanguageDto(
         Long id,
         String name,
         Level level) {}