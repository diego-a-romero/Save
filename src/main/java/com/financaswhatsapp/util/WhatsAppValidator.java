package com.financaswhatsapp.util;

public class WhatsAppValidator {
    public static boolean isValidPhoneNumber(String numero) {
        if (numero == null || numero.isBlank()) {
            return false;
        }

        // Remover espaços e caracteres especiais
        numero = numero.replaceAll("[^\\d+]", "");

        // Verificar se começa com "+" ou um número entre 1 e 9
        if (!numero.startsWith("+") && (numero.charAt(0) < '1' || numero.charAt(0) > '9')) {
            return false;
        }

        // Contar quantidade de dígitos (máximo 15)
        int totalDigitos = numero.startsWith("+") ? numero.length() - 1 : numero.length();
        return totalDigitos >= 10 && totalDigitos <= 15;
    }
}
