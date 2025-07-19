package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CriarItemCardapioDtoRequest {

    @NotNull(message = "é obrigatório")
    private Long idCardapio;

    @NotNull(message = "é obrigatório")
    private String nomePrato;

    @NotNull(message = "é obrigatório")
    private String descricaoPrato;

    @NotNull(message = "é obrigatório")
    private Double preco;

    @NotNull(message = "é obrigatório")
    private Boolean disponivelApenasLocal;

    @NotNull(message = "é obrigatório")
    private String linkImagemPrato;

}
