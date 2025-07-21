package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IHorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaItensCardapioRestauranteUseCaseTest {

    @Mock
    private IItemCardapioRestauranteRepository itemCardapioRestauranteRepository;

    @InjectMocks
    private ConsultaItensCardapioRestauranteUseCase useCase;


    @Test
    void deveConsultarItensDoCardapioComSucesso(){
        //arrange
        Long idCardapio =  1L;
        Pageable pageable = Pageable.ofSize(1);

        var itemCardapio = List.of(ItemCardapio.builder()
                .idItemCardapio(1L)
                .nome("item")
                .preco(10.0)
                .descricao("descricao")
                .build());

        Page<ItemCardapio> page = new PageImpl<>(itemCardapio, pageable, pageable.getPageSize());

        when(itemCardapioRestauranteRepository.listarItensCardapio(idCardapio, pageable))
                .thenReturn(page);

        //Act
        var resultado = useCase.run(idCardapio, pageable);

        //assert
        assertNotNull(resultado);
        verify(itemCardapioRestauranteRepository).listarItensCardapio(idCardapio, pageable);
    }
}
