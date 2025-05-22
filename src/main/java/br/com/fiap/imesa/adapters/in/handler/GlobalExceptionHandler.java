package br.com.fiap.imesa.adapters.in.handler;


import br.com.fiap.imesa.adapters.in.handler.dto.ErroResponse;
import br.com.fiap.imesa.application.core.domain.exception.AlteracaoSenhaDivergenteException;
import br.com.fiap.imesa.application.core.domain.exception.LoginInvalidoException;
import br.com.fiap.imesa.application.core.domain.exception.UsuarioNaoEncontradoException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<?> handleUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException ex) {

        ErroResponse error = new ErroResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AlteracaoSenhaDivergenteException.class)
    public ResponseEntity<?> alteracaoSenhaDivergenteException(
            AlteracaoSenhaDivergenteException ex) {

        ErroResponse error = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<?> loginInvalidoException(
            LoginInvalidoException ex) {

        ErroResponse error = new ErroResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


}
