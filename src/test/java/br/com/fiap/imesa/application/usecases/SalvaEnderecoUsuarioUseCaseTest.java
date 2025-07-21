package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoRequest;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.GravarEnderecoCommand;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SalvaEnderecoUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;
    @Mock
    private IEnderecoRepository enderecoRepository;

    @InjectMocks
    private SalvaEnderecoUsuarioUseCase useCase;


    @Test
    void deveSalvarEnderecoComSucesso(){
        //arrange

        var command = GravarEnderecoCommand.builder()
                .usuarioId(1L)
                .enderecoDtoRequest(
                        EnderecoDtoRequest.builder()
                                .logradouro("logradouro")
                                .build()
                )
                .build();

        var usuarioDomain = Usuario.builder()
                .id(1L)
                .nome("nome")
                .email("email")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.of(usuarioDomain));

        when(enderecoRepository.consultarPorIdDeUsuario(any()))
                .thenReturn(Optional.empty());

        when(enderecoRepository.salvar(any(Endereco.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        useCase.run(command);

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(enderecoRepository).consultarPorIdDeUsuario(any());
        verify(enderecoRepository).salvar(any(Endereco.class));
    }

    @Test
    void deveAtualizarEnderecoComSucesso(){
        //arrange

        var command = GravarEnderecoCommand.builder()
                .usuarioId(1L)
                .enderecoDtoRequest(
                        EnderecoDtoRequest.builder()
                                .logradouro("logradouro")
                                .build()
                )
                .build();

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

        when(enderecoRepository.salvar(any(Endereco.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        useCase.run(command);

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(enderecoRepository).consultarPorIdDeUsuario(any());
        verify(enderecoRepository).salvar(any(Endereco.class));
    }

    @Test
    void deveLancarExcecaoDevidoUsuarioNaoEncontrado() {
        //arrange
        var command = GravarEnderecoCommand.builder()
                .usuarioId(1L)
                .enderecoDtoRequest(
                        EnderecoDtoRequest.builder()
                                .logradouro("logradouro")
                                .build()
                )
                .build();

        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(command));

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(enderecoRepository, never()).consultarPorIdDeUsuario(any());
        verify(enderecoRepository, never()).salvar(any());
        verify(usuarioRepository, never()).criar(any());
    }
}
