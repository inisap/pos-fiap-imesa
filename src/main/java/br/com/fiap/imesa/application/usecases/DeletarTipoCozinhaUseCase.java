package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoCozinhaPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IDeletaTipoCozinhaRepository;
import br.com.fiap.imesa.domain.gateway.IDeletaTipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarTipoCozinhaUseCase {
    private final IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository;
    private final IDeletaTipoCozinhaRepository deletaTipoCozinhaRepository;

    public DeletarTipoCozinhaUseCase(IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository,
                                     IDeletaTipoCozinhaRepository deletaTipoCozinhaRepository) {
        this.consultaTipoCozinhaPorIdRepository = consultaTipoCozinhaPorIdRepository;
        this.deletaTipoCozinhaRepository = deletaTipoCozinhaRepository;
    }

    public void run(Integer idTipoCozinha){

        var tipoCozinhaDeletar = consultaTipoCozinhaPorIdRepository.consultar(idTipoCozinha)
                .orElseThrow(() -> new TipoCozinhaNaoEncontradoException(null, idTipoCozinha));

        deletaTipoCozinhaRepository.deletar(tipoCozinhaDeletar);
    }
}
