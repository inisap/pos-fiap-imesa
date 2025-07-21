package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.HorarioFuncionamentoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoValidoParaRestauranteException;
import br.com.fiap.imesa.application.usecases.command.FuncionamentoCommand;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IHorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SalvaRestauranteUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;
    @Mock
    private ITipoCozinhaRepository tipoCozinhaRepository;
    @Mock
    private IRestauranteRepository restauranteRepository;

    @InjectMocks
    private SalvaRestauranteUsuarioUseCase useCase;


    @Test
    void deveSalvarHorarioComSucesso(){
        //arrange
        var command = GravarRestauranteCommand.builder()
                .usuarioId(1L)
                .build();

        var usuarioDomain = Usuario.builder()
                        .id(1L)
                        .login("login")
                        .tipoUsuario(TipoUsuario.builder()
                        .id(1)
                        .build())
                        .build();

        var tipoCozinhaDOmain = TipoCozinha.builder()
                .nome("Cozinha Italiana")
                .id(1)
                        .build();


        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.of(usuarioDomain));

        when(tipoCozinhaRepository.consultarPorIdTipoCozinha(any()))
                .thenReturn(Optional.of(tipoCozinhaDOmain));

        when(restauranteRepository.salvar(any()))
                .thenAnswer(i -> i.getArgument(0));

        //Act
        useCase.run(command);

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(tipoCozinhaRepository).consultarPorIdTipoCozinha(any());
        verify(restauranteRepository).salvar(any());
    }

    @Test
    void deveLancarExcecaoDevidoUsuarioNaoEncontrado() {
        //arrange
        var command = GravarRestauranteCommand.builder()
                .usuarioId(1L)
                .build();

        var usuarioDomain = Usuario.builder()
                .id(1L)
                .login("login")
                .build();

        var tipoCozinhaDOmain = TipoCozinha.builder()
                .nome("Cozinha Italiana")
                .id(1)
                .build();


        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(command));

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(tipoCozinhaRepository, never()).consultarPorIdTipoCozinha(any());
        verify(restauranteRepository, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoDevidoUsuarioNaoSerDonoDeRestaurante() {
        //arrange
        var command = GravarRestauranteCommand.builder()
                .usuarioId(1L)
                .build();

        var usuarioDomain = Usuario.builder()
                .id(1L)
                .login("login")
                .tipoUsuario(TipoUsuario.builder()
                        .id(2)
                        .build())
                .build();


        when(usuarioRepository.consultarPorIdUsuario(any()))
                .thenReturn(Optional.of(usuarioDomain));

        //Act
        assertThrows(UsuarioNaoValidoParaRestauranteException.class, () -> useCase.run(command));

        //assert
        verify(usuarioRepository).consultarPorIdUsuario(any());
        verify(tipoCozinhaRepository, never()).consultarPorIdTipoCozinha(any());
        verify(restauranteRepository, never()).salvar(any());
    }

}
