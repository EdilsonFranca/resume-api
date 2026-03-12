package br.com.curriculopro.infra.controller;

public record PaymentRequest(
        String email,
        Long resumeId
) {}