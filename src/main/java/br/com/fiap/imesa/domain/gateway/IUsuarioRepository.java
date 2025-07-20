package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;

import java.util.Optional;

public interface IUsuarioRepository {

    void deletar(Usuario usuario);

    Usuario criar(Usuario usuario);

    Usuario atualizar(Usuario usuario);

    Optional<Usuario> consultarPorEmail(String email);

    Optional<Usuario> consultarPorLogin(String login);

    Optional<Usuario> consultarPorIdUsuario(Long id);
}
