package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoRequest;
import br.com.fiap.imesa.application.exception.HorarioFuncionamentoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.FuncionamentoCommand;
import br.com.fiap.imesa.application.usecases.command.GravarEnderecoCommand;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IHorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
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
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class SalvaHorarioRestauranteUseCaseTest {

    @Mock
    private IHorarioFuncionamentoRepository horarioFuncionamentoRepository;

    @Mock
    private IRestauranteRepository restauranteRepository;

    @InjectMocks
    private SalvaHorarioRestauranteUseCase useCase;


    @Test
    void deveSalvarHorarioComSucesso(){
        //arrange

        List<FuncionamentoCommand> funcionamentoCommandList = new ArrayList<>();
        for (int i = 1; i < 8;  i++){
            funcionamentoCommandList.add(
                    FuncionamentoCommand.builder()
                    .diaSemana(i)
                            .horaAbertura(LocalTime.of(8,0,0, 0))
                            .horaFechamento(LocalTime.of(18,0,0, 0))
                    .build());
        }

        var command = HorarioFuncionamentoCommand.builder()
                .funcionamentoCommand(funcionamentoCommandList)
                .build();

        var restauranteDomain = Restaurante.builder()
                .id(1L)
                .nome("Casa de Massas")
                .build();

//        List<HorarioFuncionamento> horarioRestauranteDomainList = new ArrayList<>();
//        for (int i = 1; i < 8;  i++){
//            horarioRestauranteDomainList.add(
//                    HorarioFuncionamento.builder()
//                            .diaSemana(i)
//                            .horaAbertura(LocalTime.of(8,0,0))
//                            .horaFechamento(LocalTime.of(18,0,0))
//                            .build());
//        }

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.of(restauranteDomain));

        when(horarioFuncionamentoRepository.consultarPorIdDeRestaurante(any()))
                .thenReturn(new ArrayList<>());

        when(horarioFuncionamentoRepository.salvar(anyList()))
                .thenAnswer(i -> i.getArgument(0));

        //Act
        useCase.run(command);

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(horarioFuncionamentoRepository).consultarPorIdDeRestaurante(any());
        verify(horarioFuncionamentoRepository).salvar(anyList());
    }

    @Test
    void deveAtualizarHorarioComSucesso(){
        //arrange

        List<FuncionamentoCommand> funcionamentoCommandList = new ArrayList<>();
        for (int i = 1; i < 8;  i++){
            funcionamentoCommandList.add(
                    FuncionamentoCommand.builder()
                            .diaSemana(i)
                            .horaAbertura(LocalTime.of(8,0,0, 0))
                            .horaFechamento(LocalTime.of(18,0,0, 0))
                            .build());
        }

        var command = HorarioFuncionamentoCommand.builder()
                .funcionamentoCommand(funcionamentoCommandList)
                .build();

        var restauranteDomain = Restaurante.builder()
                .id(1L)
                .nome("Casa de Massas")
                .build();

        List<HorarioFuncionamento> horarioRestauranteDomainList = new ArrayList<>();
        for (int i = 1; i < 8;  i++){
            horarioRestauranteDomainList.add(
                    HorarioFuncionamento.builder()
                            .diaSemana(i)
                            .horaAbertura(LocalTime.of(8,0,0))
                            .horaFechamento(LocalTime.of(18,0,0))
                            .build());
        }

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.of(restauranteDomain));

        when(horarioFuncionamentoRepository.consultarPorIdDeRestaurante(any()))
                .thenReturn(horarioRestauranteDomainList);

        when(horarioFuncionamentoRepository.salvar(anyList()))
                .thenAnswer(i -> i.getArgument(0));

        //Act
        useCase.run(command);

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(horarioFuncionamentoRepository).consultarPorIdDeRestaurante(any());
        verify(horarioFuncionamentoRepository).salvar(anyList());
    }

    @Test
    void deveLancarExcecaoDevidoQuantidadeDiasInvalida() {
        //arrange
        List<FuncionamentoCommand> funcionamentoCommandList = new ArrayList<>();
        for (int i = 1; i < 7;  i++){
            funcionamentoCommandList.add(
                    FuncionamentoCommand.builder()
                            .diaSemana(i)
                            .horaAbertura(LocalTime.of(8,0,0, 0))
                            .horaFechamento(LocalTime.of(18,0,0, 0))
                            .build());
        }

        var command = HorarioFuncionamentoCommand.builder()
                .funcionamentoCommand(funcionamentoCommandList)
                .build();

        var restauranteDomain = Restaurante.builder()
                .id(1L)
                .nome("Casa de Massas")
                .build();

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.of(restauranteDomain));

        //Act
        assertThrows(HorarioFuncionamentoException.class, () -> useCase.run(command));

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(horarioFuncionamentoRepository, never()).consultarPorIdDeRestaurante(any());
        verify(horarioFuncionamentoRepository, never()).salvar(anyList());
    }

    @Test
    void deveLancarExcecaoDevidoRestauranteNaoEncontrado() {
        //arrange
        List<FuncionamentoCommand> funcionamentoCommandList = new ArrayList<>();
        for (int i = 1; i < 7;  i++){
            funcionamentoCommandList.add(
                    FuncionamentoCommand.builder()
                            .diaSemana(i)
                            .horaAbertura(LocalTime.of(8,0,0, 0))
                            .horaFechamento(LocalTime.of(18,0,0, 0))
                            .build());
        }

        var command = HorarioFuncionamentoCommand.builder()
                .funcionamentoCommand(funcionamentoCommandList)
                .build();

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.run(command));

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(horarioFuncionamentoRepository, never()).consultarPorIdDeRestaurante(any());
        verify(horarioFuncionamentoRepository, never()).salvar(anyList());
    }

    @Test
    void deveLancarExcecaoDevidoNaoTerHoraInicial() {
        //arrange
        List<FuncionamentoCommand> funcionamentoCommandList = new ArrayList<>();
        for (int i = 1; i < 8;  i++){
            funcionamentoCommandList.add(
                    FuncionamentoCommand.builder()
                            .diaSemana(i)
                            .horaFechamento(LocalTime.of(18,0,0, 0))
                            .build());
        }

        var command = HorarioFuncionamentoCommand.builder()
                .funcionamentoCommand(funcionamentoCommandList)
                .build();

        var restauranteDomain = Restaurante.builder()
                .id(1L)
                .nome("Casa de Massas")
                .build();

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.of(restauranteDomain));


        //Act
        assertThrows(HorarioFuncionamentoException.class, () -> useCase.run(command));

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(horarioFuncionamentoRepository, never()).consultarPorIdDeRestaurante(any());
        verify(horarioFuncionamentoRepository, never()).salvar(anyList());
    }

    @Test
    void deveLancarExcecaoDevidoNaoTerHoraFinal() {
        //arrange
        List<FuncionamentoCommand> funcionamentoCommandList = new ArrayList<>();
        for (int i = 1; i < 8;  i++){
            funcionamentoCommandList.add(
                    FuncionamentoCommand.builder()
                            .diaSemana(i)
                            .horaAbertura(LocalTime.of(18,0,0, 0))
                            .build());
        }

        var command = HorarioFuncionamentoCommand.builder()
                .funcionamentoCommand(funcionamentoCommandList)
                .build();

        var restauranteDomain = Restaurante.builder()
                .id(1L)
                .nome("Casa de Massas")
                .build();

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.of(restauranteDomain));


        //Act
        assertThrows(HorarioFuncionamentoException.class, () -> useCase.run(command));

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(horarioFuncionamentoRepository, never()).consultarPorIdDeRestaurante(any());
        verify(horarioFuncionamentoRepository, never()).salvar(anyList());
    }

}
