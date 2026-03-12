package br.com.curriculopro.application.factories;

import br.com.curriculopro.application.strategies.GeradorPDFStrategy;
import br.com.curriculopro.domain.enums.Modelo;

public class GeradorPDFFactory {

    public static GeradorPDFStrategy criar(Modelo modelo) {
        return switch (modelo) {
            case M1  -> new GeradorPDFM1();
            case M2  -> new GeradorPDFM2();
            case M3  -> new GeradorPDFM3();
            default -> new GeradorPDFM1();
        };
    }
}