package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;

import java.util.Optional;

public interface IConsultaUsuarioPorIdRepository {

    Optional<Usuario> consultar(Long id);
}
