package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.IConsultaTodosTipoCozinhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaTiposCozinhaUseCase {
    private final IConsultaTodosTipoCozinhaRepository consultaTodosTipoCozinhaRepository;

    public ConsultaTiposCozinhaUseCase(IConsultaTodosTipoCozinhaRepository consultaTodosTipoCozinhaRepository) {
        this.consultaTodosTipoCozinhaRepository = consultaTodosTipoCozinhaRepository;
    }

    public List<TipoCozinha> run(){

        return consultaTodosTipoCozinhaRepository.consultar();
    }
}
