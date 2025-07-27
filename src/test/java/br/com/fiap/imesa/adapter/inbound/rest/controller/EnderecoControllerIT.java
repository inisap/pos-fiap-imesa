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
@Sql(scripts = "/data-teste-enderecos-controller.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnderecoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }

    @Order(1)
    @Test
    void deveCriarUmEnderecoAssociadoUsuario() throws Exception {

        String jsonRequest = """
            {
                "logradouro": "Rua da Casa do Vint e Dois",
                "numero": "22",
                "complemento": "loja 22",
                "cep": "06364550",
                "cidade": "Carapicuiba",
                "bairro": "Jardim Ana Estela",
                "estado": "São Paulo"
            }
            """;

        mockMvc.perform(put("/v1/users/{userId}/enderecos",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.logradouro").value("Rua da Casa do Vint e Dois"))
                .andExpect(jsonPath("$.numero").value("22"))
                .andExpect(jsonPath("$.complemento").value("loja 22"))
                .andExpect(jsonPath("$.cep").value("06364550"))
                .andExpect(jsonPath("$.cidade").value("Carapicuiba"))
                .andExpect(jsonPath("$.bairro").value("Jardim Ana Estela"))
                .andExpect(jsonPath("$.estado").value("São Paulo"));
    }

    @Order(2)
    @Test
    void deveConsultarEnderecoAssociadoAoUsuario() throws Exception {

        mockMvc.perform(get("/v1/users/{userId}/enderecos",2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

