package br.com.fiap.imesa.outbound.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.impl.UsuarioRepositoryJpaAdapter;
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
@Sql(scripts = "/data-teste-usuario.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryJpaAdapterIT {

    @Autowired
    private UsuarioRepositoryJpaAdapter usuarioRepositoryJpaAdapter;

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

        var usuarioNovo = Usuario.builder()
                .tipoUsuario(tipoUsuario)
                .login("teste_login")
                .email("teste@teste.com.br")
                .nome("nome teste")
                .password("senhaTeste123")
                .build();

        var usuarioAtualizar = Usuario.builder()
                .id(1L)
                .tipoUsuario(tipoUsuario)
                .login("teste_login")
                .email("teste@teste.com.br")
                .nome("nome teste atualizado")
                .password("senhaTeste123")
                .build();

        // [2] salvar
        var salvo = usuarioRepositoryJpaAdapter.criar(usuarioNovo);

        // [3] consultar por email
        Optional<Usuario> consultarPorEmail = usuarioRepositoryJpaAdapter.consultarPorEmail(usuarioNovo.getEmail());
        assertTrue(consultarPorEmail.isPresent());

        // [4] consultar por login
        Optional<Usuario> consultarPorLogin = usuarioRepositoryJpaAdapter.consultarPorLogin(usuarioNovo.getLogin());
        assertTrue(consultarPorLogin.isPresent());

        // [5] consultar por id usuario
        Optional<Usuario> consultarPorIdUsuario = usuarioRepositoryJpaAdapter.consultarPorIdUsuario(salvo.getId());
        assertTrue(consultarPorIdUsuario.isPresent());

        // [6] Atualizar
        Usuario usuarioAtualizado = usuarioRepositoryJpaAdapter.atualizar(usuarioAtualizar);
        assertTrue(usuarioAtualizado.getNome().equalsIgnoreCase(usuarioAtualizar.getNome()));

        // [7] Deletar
        usuarioRepositoryJpaAdapter.deletar(salvo);
        assertTrue(usuarioRepositoryJpaAdapter.consultarPorIdUsuario(salvo.getId()).isEmpty());
    }
}

