package br.com.fiap.imesa.application.ports.in;

import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioResponse;

public interface CriarUsuarioPortIn {

    CriarUsuarioResponse criar(CriarUsuarioRequest criarUsuarioRequest);
}
