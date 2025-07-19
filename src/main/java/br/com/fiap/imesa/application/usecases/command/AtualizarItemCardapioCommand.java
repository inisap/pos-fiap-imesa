package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AtualizarItemCardapioCommand {

    private Long idItem ;
    private Long idCardapio;
    private String nomePrato;
    private String descricaoPrato;
    private Double preco;
    private Boolean disponivelApenasLocal;
    private String linkImagemPrato;

}
