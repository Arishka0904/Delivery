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
@Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-product-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductAdministrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductAdministrationController productAdministrationController;

    @Test
    public void shouldContainsAdminNavbar() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id=\"products\"]").string("Products"));
    }


    @Test
    public void shouldContainsCurrentProductList() throws Exception {
        this.mockMvc.perform(get("/product"))
                .andDo(print())
                .andExpect(content().string(containsString("List of product")))
                .andExpect(xpath("/html/body/div/table/tbody/tr").nodeCount(4));
    }

    @Test
    public void shouldContainsProductData() throws Exception {
        this.mockMvc.perform(get("/product/2"))
                .andDo(print())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("product"))
                .andExpect(xpath("/html/body/div/form/div[1]/div/h3").string(containsString("product2")));
    }

    @Test
    public void shouldRedirectToProductList() throws Exception {

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/product")
                .param("id", "2")
                .param("productName", "product2")
                .param("category", "SECOND_CATEGORY")
                .param("price", "10")
                .param("depth", "3")
                .param("height", "2")
                .param("weight", "2")
                .param("width", "2")
                .param("quantityOnPallet", "2")
                .param("quantityInWarehouse", "2")
                .param("currentVersion", "true")
                .with(csrf())
                .flashAttr("product", new Product());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product"));
    }

    @Test
    public void shouldContainsEmptyForm() throws Exception {
        this.mockMvc.perform(get("/product/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().size(0))
                .andExpect(model().attributeDoesNotExist("product"));

    }

    @Test
    public void shouldAddNewProductAndRedirectToProductList() throws Exception {

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/product/add")
                .param("productName", "product4")
                .param("category", "SECOND_CATEGORY")
                .param("price", "15.2")
                .param("depth", "4")
                .param("height", "4")
                .param("weight", "24")
                .param("width", "24")
                .param("quantityOnPallet", "24")
                .param("quantityInWarehouse", "24")
                .param("currentVersion", "true")
                .with(csrf())
                .flashAttr("product", new Product());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product"));
    }
}
