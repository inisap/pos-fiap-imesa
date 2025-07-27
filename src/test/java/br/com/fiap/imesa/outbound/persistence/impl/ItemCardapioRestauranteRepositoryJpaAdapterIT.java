package br.com.fiap.imesa.outbound.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.impl.ItemCardapioRestauranteRepositoryJpaAdapter;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Sql(scripts = "/data-teste-item-cardapio.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemCardapioRestauranteRepositoryJpaAdapterIT {

    @Autowired
    private ItemCardapioRestauranteRepositoryJpaAdapter itemCardapioRestauranteRepositoryJpaAdapter;

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

        var cardapio = Cardapio.builder()
                .codigoCardapio(1L)
                .descricaoCardapio("Cardapio Teste")
                .idRestaurante(restaurante)
                .build();

        var novo = ItemCardapio.builder()
                .cardapio(cardapio)
                .preco(89.90)
                .descricao("descricao item teste")
                .nome("item teste")
                .linkImagemPrato("link imagem teste")
                .diponivelApenasLocalmente(true)
                .build();

        // [2] salvar
        var salvo = itemCardapioRestauranteRepositoryJpaAdapter.salvar(novo);

        // [3] consultar
        Optional<ItemCardapio> consultado = itemCardapioRestauranteRepositoryJpaAdapter.consultar(1L);
        assertTrue(consultado.isPresent());

        Pageable pageable = PageRequest.of(0, 10);
        // [4] listar Itens cardapio
        Page<ItemCardapio> resultado = itemCardapioRestauranteRepositoryJpaAdapter.listarItensCardapio(1L, pageable);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getTotalElements()).isGreaterThan(0);
        resultado.forEach(item -> {
            assertThat(item.getNome()).isEqualTo("item teste");
        });
        // [5] Consultar por id Item cardapio
        var item = itemCardapioRestauranteRepositoryJpaAdapter.consultarPorIdItemCardapioEIdCardapio(salvo);
        assertTrue(item.isPresent());

        // [6] deletar
        itemCardapioRestauranteRepositoryJpaAdapter.deletar(salvo);
        assertTrue(itemCardapioRestauranteRepositoryJpaAdapter.consultar(salvo.getIdItemCardapio()).isEmpty());
    }
}

