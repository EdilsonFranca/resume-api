package br.com.curriculopro.infra.utils;

public class PhoneUtil {
    public static String formatar(String number) {

        number = number.replaceAll("\\D", "");

        if (number.length() == 11) {
            return "(" + number.substring(0, 2) + ") " + number.substring(2, 7) + "-" + number.substring(7);
        }

        throw new IllegalArgumentException("Número inválido");
    }
}
