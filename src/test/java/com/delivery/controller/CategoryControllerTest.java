package com.delivery.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-product-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryController categoryController;

    @Test
    public void shouldContainsTwoProductThirdCategory() throws Exception {
        this.mockMvc.perform(get("/category/THIRD_CATEGORY"))
                .andDo(print())
                .andExpect(content().string(containsString("List of product")))
                .andExpect(xpath("/html/body/div/form/table/tbody/tr").nodeCount(2));
    }
    @Test
    public void shouldNotContainsProductFirstCategory() throws Exception {
        this.mockMvc.perform(get("/category/FIRST_CATEGORY"))
                .andDo(print())
                .andExpect(content().string(containsString("List of product")))
                .andExpect(xpath("/html/body/div/form/table/tbody/tr").nodeCount(0));
    }
}
