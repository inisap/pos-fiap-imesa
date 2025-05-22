package br.com.fiap.imesa.application.ports.in;

import br.com.fiap.imesa.application.core.usecases.DeletarUsuario.dto.DeletarUsuarioRequest;

public interface DeletarUsuarioPortIn {

    void deletarUsuario(Long userId);
}
