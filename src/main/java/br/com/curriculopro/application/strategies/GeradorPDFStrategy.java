package br.com.curriculopro.application.strategies;

import br.com.curriculopro.domain.entities.Resume;

import java.io.ByteArrayOutputStream;

public interface GeradorPDFStrategy {
    ByteArrayOutputStream generatePdf(Resume resume) throws Exception;
}
