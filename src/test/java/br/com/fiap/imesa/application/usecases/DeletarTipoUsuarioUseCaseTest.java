package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletarTipoUsuarioUseCaseTest {

    @Mock
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private DeletarTipoUsuarioUseCase useCase;


    @Test
    void deveDeletarTipoUsuarioComSucesso(){
        //arrange

        Integer tipoUsuario = 1;

        var tipoUsuarioDomain = TipoUsuario.builder()
                .id(1)
                .nome("Dono de Restaurante")
                .build();

        when(tipoUsuarioRepository.consultarPorIdTipoUsuario(any()))
                .thenReturn(Optional.of(tipoUsuarioDomain));

        //Act
        useCase.run(tipoUsuario);

        //assert
        verify(tipoUsuarioRepository).consultarPorIdTipoUsuario(any());
        verify(tipoUsuarioRepository).deletar(any());
    }

    @Test
    void deveLancarExcecaoQuandotemCardapioEIdCardapioNaoExiste() {
        //arrange
        Integer tipoUsuario = 1;

        when(tipoUsuarioRepository.consultarPorIdTipoUsuario(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.run(tipoUsuario));

        //assert
        verify(tipoUsuarioRepository).consultarPorIdTipoUsuario(any());
        verify(tipoUsuarioRepository, never()).deletar(any());
    }
}
