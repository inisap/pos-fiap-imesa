package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaTiposUsuarioUseCase {
    private final ITipoUsuarioRepository tipoUsuarioRepository;

    public ConsultaTiposUsuarioUseCase(ITipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<TipoUsuario> run(){

        return tipoUsuarioRepository.consultarTodosTiposDeUsuario() ;
    }
}
