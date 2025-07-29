package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarTipoUsuarioUseCase {
    private final ITipoUsuarioRepository tipoUsuarioRepository;

    public DeletarTipoUsuarioUseCase(ITipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public void run(Integer idTipoUsuario){

        var tipoCozinhaDeletar = tipoUsuarioRepository.consultarPorIdTipoUsuario(idTipoUsuario)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(null, idTipoUsuario.toString()));

        tipoUsuarioRepository.deletar(tipoCozinhaDeletar);
    }
}
