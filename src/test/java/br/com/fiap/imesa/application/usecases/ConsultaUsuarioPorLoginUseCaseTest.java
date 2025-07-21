package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.LoginNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaUsuarioPorLoginUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private ConsultaUsuarioPorLoginUseCase useCase;


    @Test
    void deveConsultarUsuarioPorLoginComSucesso(){
        //arrange

        String login = "login";

        var usuario = Usuario.builder()
                        .id(1L)
                        .nome("usuario")
                        .password("password")
                        .login("login")
                        .build();


        when(usuarioRepository.consultarPorLogin(login))
                .thenReturn(Optional.of(usuario));

        //Act
        var resultado = useCase.run(login);

        //assert
        assertNotNull(resultado);
        verify(usuarioRepository).consultarPorLogin(login);
    }

    @Test
    void deveLancarExcecaoDeUsuarioNaoEncontradoPorLogin() {

        //arrange
        String login = "login";

        when(usuarioRepository.consultarPorLogin(login))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(LoginNaoEncontradoException.class, () -> useCase.run(login));

        //assert
        verify(usuarioRepository).consultarPorLogin(login);
    }
}
