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
@Sql(scripts = "/data-teste-restaurantes-controller.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }

    @Order(1)
    @Test
    void deveCriarUmRestaurante() throws Exception {

        String jsonRequest = """
            {
                "nome": "Bras Tratoria",
                "tipoCozinha": 1,
                "usuarioId": "1"
            }
            """;

        mockMvc.perform(post("/v1/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(2)
    @Test
    void deveAtualizarUmRestaurante() throws Exception {

        String jsonRequest = """
            {
                "nome": "Bras Tratoria 2",
                "tipoCozinha": 1
            }
            """;

        mockMvc.perform(put("/v1/restaurantes/{idRestaurante}",1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Bras Tratoria 2"))
                .andExpect(jsonPath("$.tipoCozinha").value(1))
                .andExpect(jsonPath("$.usuarioId").exists());
    }

    @Order(3)
    @Test
    void deveConsultarRestaurantePorTipoDeCozinha() throws Exception {

        mockMvc.perform(get("/v1/restaurantes?tipoCozinha={id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Order(4)
    @Test
    void deveConsultarRestaurantePorNome() throws Exception {

        mockMvc.perform(get("/v1/restaurantes?nomeRestaurante={nome}", "Restaurante Teste2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Order(5)
    @Test
    void deveDeletarRestaurante() throws Exception {

        mockMvc.perform(delete("/v1/restaurantes/{idRestaurante}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}

