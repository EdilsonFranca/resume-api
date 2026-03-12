package br.com.curriculopro.infra.controller;

import br.com.curriculopro.domain.enums.State;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ResumeDto(Long id,

                        @NotBlank(message = "Nome é obrigatório")
                        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
                        String name,

                        @NotBlank(message = "Telefone é obrigatório")
                        @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter 10 ou 11 números")
                        String phone,

                        @NotBlank(message = "Email é obrigatório")
                        @Email(message = "Email inválido")
                        String email,

                        @NotBlank(message = "Cargo é obrigatório")
                        @Size(max = 100, message = "Cargo deve ter no máximo 100 caracteres")
                        String position,

                        @NotBlank(message = "Cidade é obrigatória")
                        @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
                        String city,

                        @NotNull(message = "Estado é obrigatório")
                        State state,

                        @NotBlank(message = "cep é obrigatória")
                        @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
                        String cep
) {}
