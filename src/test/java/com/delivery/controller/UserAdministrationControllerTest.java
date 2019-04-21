package com.delivery.controller;

import com.delivery.domain.Product;
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
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@WithUserDetails("admin")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserAdministrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserAdministrationController userAdministrationController;

    @Test
    public void shouldContainsAdminNavbar() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id=\"users\"]").string("User list"));
    }

    @Test
    public void shouldContainsUserList() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(content().string(containsString("List of users")))
                .andExpect(xpath("//*[@id=\"userList\"]/tr").nodeCount(2));
    }

    @Test
    public void shouldContainsTwoRoles() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(content().string(containsString("List of users")))
                .andExpect(xpath("//*[@id=\"userList\"]/tr[2][@data-id=1]/td[2]").string(containsString("ADMIN")))
                .andExpect(xpath("//*[@id=\"userList\"]/tr[2][@data-id=1]/td[2]").string(containsString("USER")));
    }

    @Test
    public void shouldNotContainsAdminChecked() throws Exception {
        this.mockMvc.perform(get("/user/2"))
                .andDo(print())
                .andExpect(xpath("//*[@id=\"ADMIN\"]").booleanValue(false));
    }
}
