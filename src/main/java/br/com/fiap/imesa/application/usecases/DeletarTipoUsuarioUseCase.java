package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IDeletaTipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarTipoUsuarioUseCase {
    private final IConsultaTipoUsuarioPorIdRepository consultaTipoUsuarioPorIdRepository;
    private final IDeletaTipoUsuarioRepository deletaTipoUsuarioRepository;

    public DeletarTipoUsuarioUseCase(IConsultaTipoUsuarioPorIdRepository consultaTipoUsuarioPorIdRepository,
                                     IDeletaTipoUsuarioRepository deletaTipoUsuarioRepository) {
        this.consultaTipoUsuarioPorIdRepository = consultaTipoUsuarioPorIdRepository;
        this.deletaTipoUsuarioRepository = deletaTipoUsuarioRepository;
    }

    public void run(Integer idTipoUsuario){

        var tipoCozinhaDeletar = consultaTipoUsuarioPorIdRepository.consultar(idTipoUsuario)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(null, idTipoUsuario));

        deletaTipoUsuarioRepository.deletar(tipoCozinhaDeletar);
    }
}
