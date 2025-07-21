package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaTiposUsuarioUseCaseTest {

    @Mock
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private ConsultaTiposUsuarioUseCase useCase;


    @Test
    void deveConsultarTiposUsuarioComSucesso(){
        //arrange

        var tiposUsuarios = TipoUsuario.builder()
                        .id(1)
                        .nome("Dono de Restaurante")
                        .build();


        when(tipoUsuarioRepository.consultarTodosTiposDeUsuario())
                .thenReturn(Collections.singletonList(tiposUsuarios));

        //Act
        var resultado = useCase.run();

        //assert
        assertNotNull(resultado);
        verify(tipoUsuarioRepository).consultarTodosTiposDeUsuario();
    }
}
