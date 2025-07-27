package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AtualizarSenhaUsuarioRequest {

    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String senhaAntiga;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String senhaNova;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String confirmacaoSenhaNova;

}
