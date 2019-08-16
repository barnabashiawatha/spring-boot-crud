package crud.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/controller/sql/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/controller/sql/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homeUnauthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithUserDetails("local@local.com")
    public void homeLocalAuthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/crud"));
    }

    @Test
    @WithUserDetails("google@gmail.com")
    public void homeGoogleAuthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/crud"));
    }

    @Test
    public void loginPageUnauthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("auth.html"));
    }

    @Test
    @WithUserDetails("local@local.com")
    public void loginPageLocalAuthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/crud"));
    }

    @Test
    @WithUserDetails("google@gmail.com")
    public void loginPageGoogleAuthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/crud"));
    }

    @Test
    public void signUpPageUnauthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/signup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("auth.html"));
    }

    @Test
    @WithUserDetails("local@local.com")
    public void signUpPageLocalAuthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/signup"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/crud"));
    }

    @Test
    @WithUserDetails("google@gmail.com")
    public void signUpPageGoogleAuthorizedRedirect() throws Exception {
        this.mockMvc.perform(get("/signup"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/crud"));
    }
}
