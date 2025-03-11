package com.financaswhatsapp.dto;

import com.financaswhatsapp.util.WhatsAppValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.AssertTrue;

public record UsuarioCreateDTO(
        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,

        @NotBlank(message = "O número do WhatsApp não pode estar vazio.")
        String numeroWhatsapp
) {
    @AssertTrue(message = "Número de WhatsApp inválido. Use um formato internacional válido.")
    public boolean isValidWhatsAppNumber() {
        return WhatsAppValidator.isValidPhoneNumber(numeroWhatsapp);
    }
}
