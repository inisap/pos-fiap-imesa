package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaTiposCozinhaUseCase {
    private final ITipoCozinhaRepository tipoCozinhaRepository;

    public ConsultaTiposCozinhaUseCase(ITipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    public List<TipoCozinha> run(){

        return tipoCozinhaRepository.consultarTodosTiposDeCozinha();
    }
}
