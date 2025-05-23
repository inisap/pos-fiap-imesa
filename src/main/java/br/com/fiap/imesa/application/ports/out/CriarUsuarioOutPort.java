package br.com.fiap.imesa.application.ports.out;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;

public interface CriarUsuarioOutPort {

    UsuarioDomain criarUsuario(UsuarioDomain usuarioDomain);
}
