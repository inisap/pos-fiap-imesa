package br.com.fiap.imesa.outbound.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.impl.CardapioRestauranteRepositoryJpaAdapter;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Sql(scripts = "/data-teste-cardapio-restaurante.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardapioRestauranteRepositoryJpaAdapterIT {

    @Autowired
    private CardapioRestauranteRepositoryJpaAdapter cardapioRestauranteRepositoryJpaAdapter;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }


    @Test
    void deveExecutarTodosOsMetodosDoRepositorio() {
        // [1] Criar entidades

        var tipoUsuario = TipoUsuario.builder()
                .id(1)
                .nome("DONO DE RESTAURANTE")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .tipoUsuario(tipoUsuario)
                .login("teste_login")
                .email("teste@teste.com.br")
                .nome("nome teste")
                .password("senhaTeste123")
                .build();

        var tipoCozinha = TipoCozinha.builder()
                .id(1)
                .nome("Italiana Teste")
                .build();

        var restaurante = Restaurante.builder()
                .nome("Restaurante Teste")
                .usuarioProprietario(usuario)
                .id(1L)
                .tipoCozinha(tipoCozinha)
                .build();

        var novo = Cardapio.builder()
                .descricaoCardapio("Cardapio Teste")
                .idRestaurante(restaurante)
                .build();

        // [2] salvar
        var salvo = cardapioRestauranteRepositoryJpaAdapter.salvar(novo);

        // [3] consultar
        Optional<Cardapio> consultado = cardapioRestauranteRepositoryJpaAdapter.consultar(salvo.getCodigoCardapio());
        assertTrue(consultado.isPresent());

        // [4] consultarPorIdCardapioEIdRestaurante
        Optional<Cardapio> consultadoPorIds = cardapioRestauranteRepositoryJpaAdapter.consultarPorIdCardapioEIdRestaurante(salvo);
        assertTrue(consultadoPorIds.isPresent());

        // [5] deletar
        cardapioRestauranteRepositoryJpaAdapter.deletar(salvo);
        assertTrue(cardapioRestauranteRepositoryJpaAdapter.consultar(salvo.getCodigoCardapio()).isEmpty());
    }
}

