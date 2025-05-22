package br.com.fiap.imesa.application.ports.in;

import br.com.fiap.imesa.application.core.usecases.ValidadorLogin.dto.LoginRequest;
import br.com.fiap.imesa.application.core.usecases.ValidadorLogin.dto.LoginResponse;

public interface ValidaLoginPortIn {

    LoginResponse validaLogin(LoginRequest loginRequest);
}
