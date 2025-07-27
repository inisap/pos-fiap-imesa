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
@Sql(scripts = "/data-teste-cardapios-controller.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CardapiosControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }

    @Order(1)
    @Test
    void deveCriarUmCardapio() throws Exception {

        String jsonRequest = """
            {
                "descricaoCardapio":"Cardapio Italiano",
                "idRestaurante":1
            }
            """;

        mockMvc.perform(post("/v1/cardapios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(2)
    @Test
    void deveAtualizarUmCardapio() throws Exception {

        String jsonRequest = """
            {
                "descricaoCardapio":"Cardapio Frances"
            }
            """;

        mockMvc.perform(put("/v1/cardapios/{idCardapio}/restaurantes/{idRestaurante}",2L,2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idCardapio").value("2"))
                .andExpect(jsonPath("$.descricaoCardapio").value("Cardapio Frances"))
                .andExpect(jsonPath("$.idRestaurante").value("2"));
    }

    @Order(3)
    @Test
    void deveConsultarCardapioDeUmRestaurante() throws Exception {

        mockMvc.perform(get("/v1/cardapios?idRestaurante={id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idCardapio").value("3"))
                .andExpect(jsonPath("$.descricaoCardapio").value("Cardapio Portugues"))
                .andExpect(jsonPath("$.idRestaurante").value("3"));
    }


    @Order(4)
    @Test
    void deveDeletarCardapio() throws Exception {

        mockMvc.perform(delete("/v1/cardapios/{idCardapio}/restaurantes/{idRestaurante}",4L,4L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

