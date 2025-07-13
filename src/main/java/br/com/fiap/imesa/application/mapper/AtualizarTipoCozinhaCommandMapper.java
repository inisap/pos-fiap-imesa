package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.AtualizarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

public class AtualizarTipoCozinhaCommandMapper {

    public static TipoCozinha commandToDomain(AtualizarTipoCozinhaCommand command){
        return TipoCozinha.builder()
                .id(command.getId())
                .nome(command.getNome())
                .build();
    }
}
