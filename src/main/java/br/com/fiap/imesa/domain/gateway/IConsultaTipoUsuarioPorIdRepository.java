package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

import java.util.Optional;

public interface IConsultaTipoUsuarioPorIdRepository {

    Optional<TipoUsuario> consultar(Integer idTipoUsuario);
}
