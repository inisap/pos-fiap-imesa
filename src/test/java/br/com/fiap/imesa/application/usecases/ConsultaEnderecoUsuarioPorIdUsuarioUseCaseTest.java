package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.EnderecoNaoEncontradoParaUsuarioException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IEnderecoRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaEnderecoUsuarioPorIdUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;
    @Mock
    private IEnderecoRepository enderecoRepository;

    @InjectMocks
    private ConsultaEnderecoUsuarioPorIdUsuarioUseCase useCase;


    @Test
    void deveConsultarComSucesso(){
        //arrange
        Long idUsuario =  1L;

        var usuario = Usuario.builder()
                .id(1L)
                .nome("usuario")
                .build();

        var endereco = Endereco.builder()
                        .id(1L)
                        .cep("06364550")
                        .logradouro("logradouro")
                        .build();

        when(usuarioRepository.consultarPorIdUsuario(idUsuario))
                .thenReturn(Optional.of(usuario));


        when(enderecoRepository.consultarPorIdDeUsuario(idUsuario))
                .thenReturn(Optional.of(endereco));

        //Act
        var resultado = useCase.run(idUsuario);

        //assert
        assertNotNull(resultado);
        verify(usuarioRepository).consultarPorIdUsuario(idUsuario);
        verify(enderecoRepository).consultarPorIdDeUsuario(idUsuario);
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
        verify(enderecoRepository, never()).consultarPorIdDeUsuario(any());
    }

    @Test
    void deveLancarExcecaoDeEnderecoNaoEncontrado() {

        //arrange
        Long idUsuario =  1L;

        var usuario = Usuario.builder()
                .id(1L)
                .nome("usuario")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(idUsuario))
                .thenReturn(Optional.of(usuario));

        when(enderecoRepository.consultarPorIdDeUsuario(idUsuario))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(EnderecoNaoEncontradoParaUsuarioException.class, () -> useCase.run(idUsuario));

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(idUsuario);
        verify(enderecoRepository).consultarPorIdDeUsuario(idUsuario);
    }
}
