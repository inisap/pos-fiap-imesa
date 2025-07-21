package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
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
public class ConsultaTiposCozinhaUseCaseTest {

    @Mock
    private ITipoCozinhaRepository tipoCozinhaRepository;

    @InjectMocks
    private ConsultaTiposCozinhaUseCase useCase;


    @Test
    void deveConsultarTipoDeCozinhaComSucesso(){
        //arrange

        var tiposCozinhas = TipoCozinha.builder()
                        .id(1)
                        .nome("Casa Italiana")
                        .build();


        when(tipoCozinhaRepository.consultarTodosTiposDeCozinha())
                .thenReturn(Collections.singletonList(tiposCozinhas));

        //Act
        var resultado = useCase.run();

        //assert
        assertNotNull(resultado);
        verify(tipoCozinhaRepository).consultarTodosTiposDeCozinha();
    }
}
