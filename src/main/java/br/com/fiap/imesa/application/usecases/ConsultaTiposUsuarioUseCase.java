package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.IConsultaTodosTipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaTiposUsuarioUseCase {
    private final IConsultaTodosTipoUsuarioRepository consultaTodosTipoUsuarioRepository;

    public ConsultaTiposUsuarioUseCase(IConsultaTodosTipoUsuarioRepository consultaTodosTipoUsuarioRepository) {
        this.consultaTodosTipoUsuarioRepository = consultaTodosTipoUsuarioRepository;
    }

    public List<TipoUsuario> run(){

        return consultaTodosTipoUsuarioRepository.consultar();
    }
}
