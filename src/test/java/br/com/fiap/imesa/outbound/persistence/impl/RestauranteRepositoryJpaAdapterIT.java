package br.com.fiap.imesa.outbound.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.impl.RestauranteRepositoryJpaAdapter;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Sql(scripts = "/data-teste-restaurante.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestauranteRepositoryJpaAdapterIT {

    @Autowired
    private RestauranteRepositoryJpaAdapter restauranteRepositoryJpaAdapter;

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

        var tipoCozinha = TipoCozinha.builder()
                .id(1)
                .nome("Italiana Teste")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .tipoUsuario(tipoUsuario)
                .login("teste_login")
                .email("teste@teste.com.br")
                .nome("nome teste")
                .password("senhaTeste123")
                .build();

        var novo = Restaurante.builder()
                .usuarioProprietario(usuario)
                .nome("Restaurante Teste")
                .tipoCozinha(tipoCozinha)
                .build();

        var restauranteAtualizar = Restaurante.builder()
                .id(1L)
                .usuarioProprietario(usuario)
                .nome("Restaurante Teste Atualizado")
                .tipoCozinha(tipoCozinha)
                .build();

        // [2] salvar
        var salvo = restauranteRepositoryJpaAdapter.salvar(novo);

        // [3] consultar todos restaurantes
        List<Restaurante> consultarTodos = restauranteRepositoryJpaAdapter.consultarTodos();
        assertFalse(consultarTodos.isEmpty());

        // [4] consultar com filtros
        List<Restaurante> consultarComFiltro = restauranteRepositoryJpaAdapter.consultarComFiltros(salvo);
        assertFalse(consultarComFiltro.isEmpty());

        // [5] consultar por id do restaurante
        Optional<Restaurante> consultarPorId = restauranteRepositoryJpaAdapter.consultaPorId(1L);
        assertFalse(consultarComFiltro.isEmpty());

        // [6] atualizar
        var atualizado = restauranteRepositoryJpaAdapter.atualizar(restauranteAtualizar);
        assertTrue(atualizado.getNome().equalsIgnoreCase(restauranteAtualizar.getNome()));
    }
}

