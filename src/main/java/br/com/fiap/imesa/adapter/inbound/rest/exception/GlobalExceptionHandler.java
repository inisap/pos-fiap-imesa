package br.com.fiap.imesa.adapter.inbound.rest.exception;

import br.com.fiap.imesa.application.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TipoUsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleTipoUsuarioNaoEncontrado(TipoUsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TipoCozinhaNaoEncontradoException.class)
    public ResponseEntity<String> handleTipoCozinhaNaoEncontrado(TipoCozinhaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(HorarioFuncionamentoException.class)
    public ResponseEntity<String> handleHorarioFuncionamento(HorarioFuncionamentoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicacaoEmailJaCadastradoException.class)
    public ResponseEntity<String> DuplicacaoEmailJaCadastrado(DuplicacaoEmailJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicacaoLoginJaCadastradoException.class)
    public ResponseEntity<String> DuplicacaoLoginJaCadastrado(DuplicacaoLoginJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNaoValidoParaRestauranteException.class)
    public ResponseEntity<String> UsuarioNaoValidoParaRestaurante(UsuarioNaoValidoParaRestauranteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(LoginNaoEncontradoException.class)
    public ResponseEntity<String> LoginNaoEncontrado(LoginNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> UsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AlteracaoSenhaDivergenteException.class)
    public ResponseEntity<String> AlteracaoSenhaDivergente(AlteracaoSenhaDivergenteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<String> LoginInvalido(LoginInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(EnderecoNaoEncontradoParaUsuarioException.class)
    public ResponseEntity<String> EnderecoNaoEncontrado(EnderecoNaoEncontradoParaUsuarioException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
