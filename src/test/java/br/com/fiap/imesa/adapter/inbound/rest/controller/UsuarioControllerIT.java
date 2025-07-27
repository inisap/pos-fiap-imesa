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
@Sql(scripts = "/data-teste-usuario-controller.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerIT {

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
                "nome":"Diego Pasini",
                "email":"diego@teste1.com.br",
                "login":"diego.pasini1",
                "password":"diego123",
                "codigoTipoUsuario":"1"
            }
            """;

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Diego Pasini"))
                .andExpect(jsonPath("$.email").value("diego@teste1.com.br"))
                .andExpect(jsonPath("$.login").value("diego.pasini1"))
                .andExpect(jsonPath("$.tipoUsuario.id").value("1"))
                .andExpect(jsonPath("$.tipoUsuario.nome").value("DONO DE RESTAURANTE"));
    }

    @Order(2)
    @Test
    void deveConsultarUsuarioPorLogin() throws Exception {

        mockMvc.perform(get("/v1/users?login=teste_login")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.nome").value("nome teste"))
                .andExpect(jsonPath("$.email").value("teste@teste.com.br"))
                .andExpect(jsonPath("$.login").value("teste_login"))
                .andExpect(jsonPath("$.tipoUsuario.id").value("1"))
                .andExpect(jsonPath("$.tipoUsuario.nome").value("DONO DE RESTAURANTE"));
    }

    @Order(3)
    @Test
    void deveConsultarUsuarioPorId() throws Exception {

        mockMvc.perform(get("/v1/users/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.nome").value("nome teste"))
                .andExpect(jsonPath("$.email").value("teste@teste.com.br"))
                .andExpect(jsonPath("$.login").value("teste_login"))
                .andExpect(jsonPath("$.tipoUsuario.id").value("1"))
                .andExpect(jsonPath("$.tipoUsuario.nome").value("DONO DE RESTAURANTE"));
    }

    @Order(4)
    @Test
    void deveAtualizarUsuario() throws Exception {

        String jsonRequest = """
            {
                "nome":"Nome Teste 4",
                "email":"diego@teste4.com.br",
                "login":"login_teste4",
                "password":"teste4",
                "codigoTipoUsuario":"1"
            }
            """;

        mockMvc.perform(patch("/v1/users/{id}",3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Nome Teste 4"))
                .andExpect(jsonPath("$.email").value("diego@teste4.com.br"))
                .andExpect(jsonPath("$.login").value("login_teste4"))
                .andExpect(jsonPath("$.tipoUsuario.id").value("1"))
                .andExpect(jsonPath("$.tipoUsuario.nome").value("DONO DE RESTAURANTE"));
    }

    @Order(5)
    @Test
    void deveDeletarUsuario() throws Exception {

        mockMvc.perform(delete("/v1/users/{idUsuario}",4L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Order(6)
    @Test
    void deveAtualizarSenhaDoUsuario() throws Exception {

        String jsonRequest = """
            {
                "senhaAntiga":"senhaTeste1234",
                "senhaNova":"nova1234",
                "confirmacaoSenhaNova":"nova1234"
            }
            """;

        mockMvc.perform(put("/v1/users/{id}/senha", 5L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Order(7)
    @Test
    void deveAutenticarUsuario() throws Exception {

        String jsonRequest = """
                {
                  "login": "teste_login6",
                  "password": "senhaTeste1234"
                }
                """;

        mockMvc.perform(post("/v1/users/auth/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("nome teste6"))
                .andExpect(jsonPath("$.login").value("teste_login6"))
                .andExpect(jsonPath("$.tipo.id").value("1"))
                .andExpect(jsonPath("$.tipo.nome").value("DONO DE RESTAURANTE"));
    }
}

