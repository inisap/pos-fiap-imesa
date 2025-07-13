package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;

public interface ICriaUsuarioRepository {

    Usuario criar(Usuario usuario);
}
