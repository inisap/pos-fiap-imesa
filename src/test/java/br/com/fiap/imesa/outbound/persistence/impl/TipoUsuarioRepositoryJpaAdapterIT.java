package br.com.fiap.imesa.outbound.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.impl.TipoUsuarioRepositoryJpaAdapter;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TipoUsuarioRepositoryJpaAdapterIT {

    @Autowired
    private TipoUsuarioRepositoryJpaAdapter tipoUsuarioRepositoryJpaAdapter;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }

    @Test
    void deveExecutarTodosOsMetodosDoRepositorio() {
        // [1] Criar entidades

        var tipoUsuario = TipoUsuario.builder()
                .nome("DONO DE RESTAURANTE")
                .build();

        // [2] salvar
        var salvo = tipoUsuarioRepositoryJpaAdapter.salvar(tipoUsuario);

        // [3] consultar todos
        List<TipoUsuario> consultarTodos = tipoUsuarioRepositoryJpaAdapter.consultarTodosTiposDeUsuario();
        assertFalse(consultarTodos.isEmpty());

        // [4] consultar por tipo
        Optional<TipoUsuario> consultarComFiltro = tipoUsuarioRepositoryJpaAdapter.consultarPorIdTipoUsuario(1);
        assertTrue(consultarComFiltro.isPresent());

        // [5] consultar por id do restaurante
        tipoUsuarioRepositoryJpaAdapter.deletar(salvo);
        assertTrue(tipoUsuarioRepositoryJpaAdapter.consultarPorIdTipoUsuario(1).isEmpty());
    }
}

