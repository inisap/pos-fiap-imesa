package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.EnderecoNaoEncontradoParaUsuarioException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IEnderecoRepository;
import br.com.fiap.imesa.domain.gateway.IHorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaHorarioRestauranteUseCaseTest {

    @Mock
    private IRestauranteRepository consultaRestauranteRepository;
    @Mock
    private IHorarioFuncionamentoRepository horarioFuncionamentoRepository;

    @InjectMocks
    private ConsultaHorarioRestauranteUseCase useCase;


    @Test
    void deveConsultarHorariosComSucesso(){
        //arrange
        Long idRestaurante =  1L;

        var restaurante = Restaurante.builder()
                .id(1L)
                .nome("restaurante")
                .build();

        var horarioFuncionamento =
                HorarioFuncionamento.builder()
                                .id(1L)
                                .diaSemana(1)
                                .build();

        when(consultaRestauranteRepository.consultaPorId(idRestaurante))
                .thenReturn(Optional.of(restaurante));

        when(horarioFuncionamentoRepository.consultarPorIdDeRestaurante(idRestaurante))
                .thenReturn(Collections.singletonList(horarioFuncionamento));

        //Act
        var resultado = useCase.run(idRestaurante);

        //assert
        assertNotNull(resultado);
        verify(consultaRestauranteRepository).consultaPorId(idRestaurante);
        verify(horarioFuncionamentoRepository).consultarPorIdDeRestaurante(idRestaurante);
    }

    @Test
    void deveLancarExcecaoDeRestauranteNaoEncontrado() {

        //arrange
        Long idUsuario =  1L;

        //Act
        assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.run(idUsuario));

        //assert
        verify(consultaRestauranteRepository).consultaPorId(idUsuario);
        verify(horarioFuncionamentoRepository, never()).consultarPorIdDeRestaurante(any());
    }
}
