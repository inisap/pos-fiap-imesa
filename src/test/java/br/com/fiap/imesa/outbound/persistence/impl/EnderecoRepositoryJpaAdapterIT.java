package br.com.fiap.imesa.outbound.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.impl.EnderecoRepositoryJpaAdapter;
import br.com.fiap.imesa.domain.entities.endereco.Endereco;
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
@Sql(scripts = "/data-teste-endereco.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnderecoRepositoryJpaAdapterIT {

    @Autowired
    private EnderecoRepositoryJpaAdapter enderecoRepositoryJpaAdapter;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }
    @Test
    void deveExecutarTodosOsMetodosDoRepositorio() {
        // [1] Criar entidades

        var endereco = Endereco.builder()
                .usuarioId(1L)
                .cep("123456123")
                .logradouro("logradouro teste")
                .numero("123")
                .complemento("complemento")
                .bairro("bairro teste")
                .cidade("cidade teste")
                .estado("estado teste")
                .build();

        // [2] salvar
        var salvo = enderecoRepositoryJpaAdapter.salvar(endereco);

        // [3] consultar por idDeUsuario
        Optional<Endereco> consultado = enderecoRepositoryJpaAdapter.consultarPorIdDeUsuario(1L);
        assertTrue(consultado.isPresent());

        // [5] deletar
        enderecoRepositoryJpaAdapter.deletar(salvo);
        assertTrue(enderecoRepositoryJpaAdapter.consultarPorIdDeUsuario(salvo.getUsuarioId()).isEmpty());
    }
}

