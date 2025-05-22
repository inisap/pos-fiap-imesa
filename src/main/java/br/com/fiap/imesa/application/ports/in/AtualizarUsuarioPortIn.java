package br.com.fiap.imesa.application.ports.in;

import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioResponse;

public interface AtualizarUsuarioPortIn {

    AtualizarUsuarioResponse atualizar(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest);
}
