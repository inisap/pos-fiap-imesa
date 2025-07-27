package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.endereco.Endereco;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IEnderecoRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletarUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;
    @Mock
    private IEnderecoRepository enderecoRepository;

    @InjectMocks
    private DeletarUsuarioUseCase useCase;


    @Test
    void deveDeletarUsuarioEEnderecoComSucesso(){
        //arrange

        Long idUsuario = 1L;

        var usuarioDomain = Usuario.builder()
                .id(1L)
                .nome("nome")
                .email("email")
                .build();

        var enderecoDomain = Endereco.builder()
                .id(1L)
                .cep("06364550")
                .logradouro("endereco")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.of(usuarioDomain));

        when(enderecoRepository.consultarPorIdDeUsuario(any()))
                .thenReturn(Optional.of(enderecoDomain));

        //Act
        useCase.run(idUsuario);

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(enderecoRepository).consultarPorIdDeUsuario(any());
        verify(enderecoRepository).deletar(any());
        verify(usuarioRepository).deletar(any());
    }

    @Test
    void deveDeletarSomenteUsuarioComSucessoPoisNaotemEndereco(){
        //arrange

        Long idUsuario = 1L;

        var usuarioDomain = Usuario.builder()
                .id(1L)
                .nome("nome")
                .email("email")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.of(usuarioDomain));

        when(enderecoRepository.consultarPorIdDeUsuario(any()))
                .thenReturn(Optional.empty());

        //Act
        useCase.run(idUsuario);

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(enderecoRepository).consultarPorIdDeUsuario(any());
        verify(enderecoRepository, never()).deletar(any());
        verify(usuarioRepository).deletar(any());
    }

    @Test
    void deveLancarExcecaoDevidoUsuarioNaoExiste() {
        //arrange
        Long idUsuario = 1L;

        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(idUsuario));

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(enderecoRepository, never()).consultarPorIdDeUsuario(any());
        verify(enderecoRepository, never()).deletar(any());
        verify(usuarioRepository, never()).deletar(any());
    }
}
