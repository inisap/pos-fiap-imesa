package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

public interface IDeletaTipoUsuarioRepository {

    void deletar(TipoUsuario tipoUsuario);
}
