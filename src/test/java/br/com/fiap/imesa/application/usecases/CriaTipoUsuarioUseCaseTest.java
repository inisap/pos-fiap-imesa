package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.usecases.command.CriarTipoCozinhaCommand;
import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CriaTipoUsuarioUseCaseTest {

    @Mock
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private CriaTipoUsuarioUseCase useCase;

    @Test
    void deveCriarTipoUsuarioComSucesso(){
        //arrange

        var command = CriarTipoUsuarioCommand.builder()
                        .nome("Dono de Restaurante")
                        .build();

        when(tipoUsuarioRepository.salvar(any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(tipoUsuarioRepository).salvar(any(TipoUsuario.class));
    }
}
