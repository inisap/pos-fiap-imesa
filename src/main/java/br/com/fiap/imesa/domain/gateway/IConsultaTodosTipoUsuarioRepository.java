package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface IConsultaTodosTipoUsuarioRepository {

    List<TipoUsuario> consultar();
}
