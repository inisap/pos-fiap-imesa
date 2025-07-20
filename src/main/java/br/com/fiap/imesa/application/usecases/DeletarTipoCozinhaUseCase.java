package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarTipoCozinhaUseCase {
    private final ITipoCozinhaRepository tipoCozinhaRepository;

    public DeletarTipoCozinhaUseCase(ITipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    public void run(Integer idTipoCozinha){

        var tipoCozinhaDeletar = tipoCozinhaRepository.consultarPorIdTipoCozinha(idTipoCozinha)
                .orElseThrow(() -> new TipoCozinhaNaoEncontradoException(null, idTipoCozinha));

        tipoCozinhaRepository.deletar(tipoCozinhaDeletar);
    }
}
