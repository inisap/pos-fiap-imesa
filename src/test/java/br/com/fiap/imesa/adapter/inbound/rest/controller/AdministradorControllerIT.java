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
@Sql(scripts = "/data-teste-adm-controller.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministradorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:h2:mem:testdb-" + UUID.randomUUID() + ";MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
    }

    @Order(1)
    @Test
    void deveCriarUsuario() throws Exception {

        String jsonRequest = """
            {
              "descricaoTipoUsuario": "DONO DE RESTAURANTE"
            }
            """;

        mockMvc.perform(post("/v1/adm/tipos-usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.descricaoTipoUsuario").value("DONO DE RESTAURANTE"));
    }

    @Order(2)
    @Test
    void deveAtualizarTipoDeUsuario() throws Exception {

        String jsonRequest = """
            {
              "descricaoTipoUsuario": "ADMINISTRADOR"
            }
            """;

        mockMvc.perform(put("/v1/adm/tipos-usuarios/{idTipoUsuario}",2L)

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.descricaoTipoUsuario").value("ADMINISTRADOR"));
    }

    @Order(3)
    @Test
    void deveConsultarTodosTipoDeUsuario() throws Exception {

        mockMvc.perform(get("/v1/adm/tipos-usuarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(4)
    @Test
    void deveDeletarTipoDeUsuario() throws Exception {

        mockMvc.perform(delete("/v1/adm/tipos-usuarios/{idTipoUsuario}",3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Order(5)
    @Test
    void deveConsultarTodosTipoDeCozinha() throws Exception {

        mockMvc.perform(get("/v1/adm/tipos-cozinhas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(6)
    @Test
    void deveCriarTipoDeCozinha() throws Exception {

        String jsonRequest = """
                {
                  "nome": "ITALIANA"
                }
                """;

        mockMvc.perform(post("/v1/adm/tipos-cozinhas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.descricaoTipoCozinha").value("ITALIANA"));
    }

    @Order(7)
    @Test
    void deveAtualizarTipoDeCozinha() throws Exception {

        String jsonRequest = """
            {
              "nome": "FRANCESA"
            }
            """;

        mockMvc.perform(put("/v1/adm/tipos-cozinhas/{idTipoCozinha}",2L)

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.descricaoTipoCozinha").value("FRANCESA"));
    }

    @Order(8)
    @Test
    void deveDeletarTipoDeCozinha() throws Exception {

        mockMvc.perform(delete("/v1/adm/tipos-cozinhas/{idTipoCozinha}",3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

