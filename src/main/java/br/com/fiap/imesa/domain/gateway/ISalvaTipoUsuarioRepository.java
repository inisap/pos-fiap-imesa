package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

public interface ISalvaTipoUsuarioRepository {

    TipoUsuario salvar(TipoUsuario tipoUsuario);
}
