package crud.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/controller/sql/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/controller/sql/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validLoginRequest() throws Exception {
        var loginRequest = Files.readString(Paths.get("src/test/resources/controller/json/loginRequest-invalid-credentials.json"));

        var result = mockMvc.perform(post("/auth/login")
                .contentType(APPLICATION_JSON)
                .content(loginRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        var responseContent = result.getResponse().getContentAsString();

        assertEquals("{\"accessToken\":", responseContent.substring(0, 15));
    }

    @Test
    public void invalidLoginRequest() throws Exception {
        var loginRequest = Files.readString(Paths.get("src/test/resources/controller/json/loginRequest-valid-credentials.json"));

        mockMvc.perform(post("/auth/login")
                .contentType(APPLICATION_JSON)
                .content(loginRequest))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
