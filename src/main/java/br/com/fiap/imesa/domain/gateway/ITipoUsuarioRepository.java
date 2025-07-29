package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface ITipoUsuarioRepository {

    Optional<TipoUsuario> consultarPorIdTipoUsuario(Integer idTipoUsuario);

    TipoUsuario salvar(TipoUsuario tipoUsuario);

    List<TipoUsuario> consultarTodosTiposDeUsuario();

    void deletar(TipoUsuario tipoUsuario);

    Optional<TipoUsuario> consultarPorNome(String nomeTipo);
}
