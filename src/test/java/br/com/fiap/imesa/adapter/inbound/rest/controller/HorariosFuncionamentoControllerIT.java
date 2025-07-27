package br.com.fiap.imesa.adapter.inbound.rest.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = "/data-teste-horarios-funcionamento-controller.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HorariosFuncionamentoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }

    @Order(1)
    @Test
    void deveCriarHorariosDeFuncionamento() throws Exception {

        String jsonRequest = """
            {
                "horariosFuncionamento": [
                    {
                        "diaSemana": 1,
                        "horaAbertura": "08:00",
                        "horaFechamento": "18:00",
                        "flagDiaAberto": true
                    },
                    {
                        "diaSemana": 2,
                        "horaAbertura": "08:00",
                        "horaFechamento": "18:00",
                        "flagDiaAberto": true
                    },
                    {
                        "diaSemana": 3,
                        "horaAbertura": "08:00",
                        "horaFechamento": "18:00",
                        "flagDiaAberto": true
                    },
                    {
                        "diaSemana": 4,
                        "horaAbertura": "08:00",
                        "horaFechamento": "18:00",
                        "flagDiaAberto": true
                    },
                    {
                        "diaSemana": 5,
                        "horaAbertura": "08:00",
                        "horaFechamento": "18:00",
                        "flagDiaAberto": true
                    },
                    {
                        "diaSemana": 6,
                        "horaAbertura": "10:00",
                        "horaFechamento": "22:00",
                        "flagDiaAberto": true
                    },
                    {
                        "diaSemana": 7,
                        "horaAbertura": "10:00",
                        "horaFechamento": "12:00",
                        "flagDiaAberto": true
                    }
                ]
            }
            """;

        mockMvc.perform(put("/v1/restaurantes/{restauranteId}/horarios-funcionamentos", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(2)
    @Test
    void deveConsultarHorarioDeFuncionamentoDeUmRestaurante() throws Exception {

        mockMvc.perform(get("/v1/restaurantes/{restauranteId}/horarios-funcionamentos", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

