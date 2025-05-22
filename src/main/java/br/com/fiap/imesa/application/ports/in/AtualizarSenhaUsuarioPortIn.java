package br.com.fiap.imesa.application.ports.in;

import br.com.fiap.imesa.application.core.usecases.AtualizarSenhaUsuario.dto.AtualizarSenhaUsuarioRequest;

public interface AtualizarSenhaUsuarioPortIn {

    void atualizar(Long id, AtualizarSenhaUsuarioRequest atualizarSenhaUsuarioRequest);
}
