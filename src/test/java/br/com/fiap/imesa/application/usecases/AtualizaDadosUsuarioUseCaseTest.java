package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.*;
import br.com.fiap.imesa.application.usecases.command.AtualizarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AtualizaDadosUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    AtualizaDadosUsuarioUseCase useCase;

    private AtualizarUsuarioCommand command;

    @Test
    void deveAlturalizarUsuarioComSucesso(){

        command = AtualizarUsuarioCommand.builder()
                .idUsuario(1L)
                .nome("novoNome")
                .email("novo_email@teste.com.br")
                .login("novo_login")
                .build();

        var tipoUsuario = TipoUsuario.builder()
                .id(1)
                .nome("Dono de Restaurante")
                .build();

        var usuarioExistente = Usuario.builder()
                .id(1L)
                .nome("nomeAntigo")
                .email("teste@teste.com.br")
                .login("usuario_teste")
                .password("1234")
                .dataAlteracao(LocalDateTime.now())
                .tipoUsuario(tipoUsuario)
                 .build();

        when(usuarioRepository.consultarPorIdUsuario(command.getIdUsuario()))
                .thenReturn(Optional.of(usuarioExistente));

        when(usuarioRepository.consultarPorLogin(command.getLogin()))
                .thenReturn(Optional.empty());

        when(usuarioRepository.consultarPorEmail(command.getEmail()))
                .thenReturn(Optional.empty());

        when(usuarioRepository.atualizar(any())).thenAnswer(invocation -> invocation.getArgument(0));


        var retorno = useCase.run(command);

        assertEquals("novoNome", retorno.getNome());
        assertEquals("novo_email@teste.com.br", retorno.getEmail());
        assertEquals("novo_login", retorno.getLogin());

        verify(usuarioRepository).consultarPorIdUsuario(1L);
        verify(usuarioRepository).consultarPorLogin("novo_login");
        verify(usuarioRepository).consultarPorEmail("novo_email@teste.com.br");
        verify(usuarioRepository).atualizar(usuarioExistente);
    }


    @Test
    void deveLancarExcecaoSeUsuarioNaoForEncontrado() {


        command = AtualizarUsuarioCommand.builder()
                .idUsuario(1L)
                .nome("Diego")
                .email("email@email.com")
                .login("diego_login")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(command));

        verify(usuarioRepository).consultarPorIdUsuario(1L);
        verify(usuarioRepository, never()).atualizar(any());
    }

    @Test
    void deveLancarExcecaoSeLoginEstiverDuplicado() {

        command = AtualizarUsuarioCommand.builder()
                .idUsuario(1L)
                .nome("Diego")
                .email("email@email.com")
                .login("loginDuplicado")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .build();

        var outroUsuario = Usuario.builder()
                .id(2L)
                .build();

        when(usuarioRepository.consultarPorIdUsuario(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.consultarPorLogin("loginDuplicado")).thenReturn(Optional.of(outroUsuario));

        assertThrows(DuplicacaoLoginJaCadastradoException.class, () -> useCase.run(command));

        verify(usuarioRepository).consultarPorLogin("loginDuplicado");
        verify(usuarioRepository, never()).atualizar(any());
    }

    @Test
    void deveLancarExcecaoSeEmailEstiverDuplicado() {

        command = AtualizarUsuarioCommand.builder()
                .idUsuario(1L)
                .nome("Diego")
                .email("emailDuplicado@email.com")
                .login("login")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .build();

        var outroUsuario = Usuario.builder()
                .id(2L)
                .build();

        when(usuarioRepository.consultarPorIdUsuario(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.consultarPorLogin("login")).thenReturn(Optional.empty());
        when(usuarioRepository.consultarPorEmail("emailDuplicado@email.com")).thenReturn(Optional.of(outroUsuario));

        assertThrows(DuplicacaoEmailJaCadastradoException.class, () -> useCase.run(command));

        verify(usuarioRepository).consultarPorEmail("emailDuplicado@email.com");
        verify(usuarioRepository, never()).atualizar(any());
    }


}
