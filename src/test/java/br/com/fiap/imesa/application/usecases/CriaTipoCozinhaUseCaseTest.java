package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.usecases.command.CriarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CriaTipoCozinhaUseCaseTest {

    @Mock
    private ITipoCozinhaRepository tipoCozinhaRepository;

    @InjectMocks
    private CriaTipoCozinhaUseCase useCase;

    @Test
    void deveCriarTipoDeCozinhaComSucesso(){
        //arrange

        var command = CriarTipoCozinhaCommand.builder()
                        .descricaoTipoCozinha("Cozinha Italiana")
                        .build();

        when(tipoCozinhaRepository.salvar(any(TipoCozinha.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(tipoCozinhaRepository).salvar(any(TipoCozinha.class));
    }
}
