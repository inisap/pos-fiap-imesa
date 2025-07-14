package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.CriarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

public class CriarTipoCozinhaCommandMapper {

    public static TipoCozinha commandToDomain(CriarTipoCozinhaCommand command){
        return TipoCozinha.builder()
                .id(null)
                .nome(command.getDescricaoTipoCozinha())
                .build();
    }
}
