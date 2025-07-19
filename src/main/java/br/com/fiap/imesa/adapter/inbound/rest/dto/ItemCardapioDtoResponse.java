package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemCardapioDtoResponse {

    private Long idItem ;
    private Long idCardapio;
    private String nomePrato;
    private String descricaoPrato;
    private Double preco;
    private Boolean disponivelApenasLocal;
    private String linkImagemPrato;

}
