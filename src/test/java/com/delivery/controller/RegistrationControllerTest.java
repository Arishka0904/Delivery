package com.delivery.controller;

import com.delivery.controller.RegistrationController;
import com.delivery.domain.dto.CaptchaResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationControllerTest {

    @MockBean
    private RestTemplate restTemplate;

    private CaptchaResponseDto response = mock(CaptchaResponseDto.class);

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnRegistrationPage() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add new user")));
    }

    @Test
    public void shouldRedirect() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User2")
                .param("password", "2")
                .param("password2", "2")
                .param("email", "nihotuko@5-mail.info")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

    }

    @Test
    public void shouldReturnUsernameErrorMessage() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User1")
                .param("password", "2")
                .param("password2", "2")
                .param("email", "nihotuko@5-mail.info")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[1]/div/div").exists())
                .andExpect(content().string(containsString("User already exists!")));
    }

    @Test
    public void shouldReturnUsernameEmptyErrorMessage() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "")
                .param("password", "2")
                .param("password2", "2")
                .param("email", "yesaduna@red-mail.top")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[1]/div/div").exists())
                .andExpect(content().string(containsString("User can not be empty")));
    }


    @Test
    public void shouldReturnEmailErrorMessage() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User3")
                .param("password", "2")
                .param("password2", "2")
                .param("email", "hena@fast-mail.one")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[4]/div/div").exists())
                .andExpect(content().string(containsString("Email already exists!")));
    }

    @Test
    public void shouldReturnPasswordErrorMessage() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User3")
                .param("password", "3")
                .param("password2", "2")
                .param("email", "suxup@first-email.net")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[2]/div/div").exists())
                .andExpect(content().string(containsString("Password are different!")));
    }
    @Test
    public void shouldReturnPasswordConfirmErrorMessage() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User3")
                .param("password", "3")
                .param("password2", "")
                .param("email", "suxup@first-email.net")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[3]/div/div").exists())
                .andExpect(content().string(containsString("Password confirmation can not be empty!")));
    }

    private void mockCaptcha() {
        Mockito.doReturn(response)
                .when(restTemplate)
                .postForObject(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.any()
                );

        Mockito.doReturn(true)
                .when(response)
                .isSuccess();
    }
}
