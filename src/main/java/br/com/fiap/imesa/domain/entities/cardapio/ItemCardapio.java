package br.com.fiap.imesa.domain.entities.cardapio;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ItemCardapio {

    private Long idItemCardapio;
    private Cardapio cardapio;
    private String nome;
    private String descricao;
    private Double preco;
    private boolean diponivelApenasLocalmente;
    private String linkImagemPrato;

}
