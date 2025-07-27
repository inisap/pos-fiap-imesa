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
@Sql(scripts = "/data-teste-itens-cardapios-controller.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItensCardapiosControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }

    @Order(1)
    @Test
    void deveCriarUmItemDoCardapio() throws Exception {

        String jsonRequest = """
            {
                "idCardapio": 1,
                "nomePrato": "pasta da nona",
                "descricaoPrato": "Macarrão feito com os melhores ingredientes",
                "preco": 89.90,
                "disponivelApenasLocal": true,
                "linkImagemPrato": "s3.us-east-1a.aws.com.br-imagem-prato"
            }
            """;

        mockMvc.perform(post("/v1/itensCardapio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(2)
    @Test
    void deveAtualizarUmItemDoCardapio() throws Exception {

        String jsonRequest = """
            {
                "nomePrato":"pasta da nona 2",
                "descricaoPrato":"Macarrão feito com os melhores ingredientes",
                "preco":90.99,
                "disponivelApenasLocal": true,
                "linkImagemPrato":"s3.us-east-1a.aws.com.br-imagem-prato"
            }
            """;

        mockMvc.perform(put("/v1/itensCardapio/{idItemCardapio}/cardapios/{idCardapio}",1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomePrato").value("pasta da nona 2"))
                .andExpect(jsonPath("$.descricaoPrato").value("Macarrão feito com os melhores ingredientes"))
                .andExpect(jsonPath("$.preco").value("90.99"))
                .andExpect(jsonPath("$.disponivelApenasLocal").value(true))
                .andExpect(jsonPath("$.linkImagemPrato").value("s3.us-east-1a.aws.com.br-imagem-prato"));
    }

    @Order(3)
    @Test
    void deveConsultarItensDeUmCardapio() throws Exception {

        mockMvc.perform(get("/v1/itensCardapio?idCardapio={id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Order(4)
    @Test
    void deveDeletarCardapio() throws Exception {

        mockMvc.perform(delete("/v1/itensCardapio/{idItemCardapio}/cardapios/{idCardapio}",2L,1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

