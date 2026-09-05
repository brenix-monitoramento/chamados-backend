package com.projects.chamados.dtos.inputs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginInputDTO(
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um formato válido de e-mail.")
        String email,
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 5, max = 20, message = "A senha deve ter entre 5 e 20 caracteres.")
        String password
) {
}
