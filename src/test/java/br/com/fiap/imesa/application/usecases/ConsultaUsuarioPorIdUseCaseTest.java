package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.EnderecoNaoEncontradoParaUsuarioException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaUsuarioPorIdUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private ConsultaUsuarioPorIdUseCase useCase;


    @Test
    void deveConsultarUsuarioPorIdComSucesso(){
        //arrange

        Long idUsuario = 1L;

        var usuario = Usuario.builder()
                        .id(1L)
                        .nome("usuario")
                        .password("password")
                        .build();


        when(usuarioRepository.consultarPorIdUsuario(idUsuario))
                .thenReturn(Optional.of(usuario));

        //Act
        var resultado = useCase.run(idUsuario);

        //assert
        assertNotNull(resultado);
        verify(usuarioRepository).consultarPorIdUsuario(idUsuario);
    }

    @Test
    void deveLancarExcecaoDeUsuarioNaoEncontrado() {

        //arrange
        Long idUsuario =  1L;

        when(usuarioRepository.consultarPorIdUsuario(idUsuario))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(idUsuario));

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(idUsuario);
    }
}
