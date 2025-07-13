package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;

public interface IDeletaUsuarioRepository {

    void deletar(Usuario usuario);
}
