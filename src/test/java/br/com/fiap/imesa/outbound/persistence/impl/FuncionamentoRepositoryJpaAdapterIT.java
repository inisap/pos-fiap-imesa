package br.com.fiap.imesa.outbound.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.impl.FuncionamentoRepositoryJpaAdapter;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Sql(scripts = "/data-teste-funcionamento.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FuncionamentoRepositoryJpaAdapterIT {

    @Autowired
    private FuncionamentoRepositoryJpaAdapter funcionamentoRepositoryJpaAdapter;

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

        var restaurante = Restaurante.builder()
                .nome("Restaurante Teste")
                .usuarioProprietario(usuario)
                .id(1L)
                .tipoCozinha(tipoCozinha)
                .build();


        List<HorarioFuncionamento> horarioFuncionamentoList = new ArrayList<>();
        for (int i = 1; i < 8;  i++){
            horarioFuncionamentoList.add(
                    HorarioFuncionamento.builder()
                            .restauranteId(restaurante)
                            .diaSemana(i)
                            .horaAbertura(LocalTime.of(8,0,0, 0))
                            .horaFechamento(LocalTime.of(18,0,0, 0))
                            .flagDiaAberto(true)
                            .build());
        }


        // [2] salvar
        var salvo = funcionamentoRepositoryJpaAdapter.salvar(horarioFuncionamentoList);

        // [3] consultar por idDeRestaurante
        List<HorarioFuncionamento> consultado = funcionamentoRepositoryJpaAdapter.consultarPorIdDeRestaurante(1L);
        assertFalse(consultado.isEmpty());
    }
}

