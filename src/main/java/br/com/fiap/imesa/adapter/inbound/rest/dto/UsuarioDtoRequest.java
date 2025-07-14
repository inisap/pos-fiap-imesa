package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UsuarioDtoRequest {

    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String nome;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String email;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String login;
    @NotNull(message = "é obrigatória")
    @NotBlank(message = "não pode ser vazio")
    private String password;
    private Integer codigoTipoUsuario;

}
