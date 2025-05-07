package com.kirubha.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirubha.productservice.controller.ProductController;
import com.kirubha.productservice.dto.ProductRequest;
import com.kirubha.productservice.dto.ProductResponse;
import com.kirubha.productservice.service.ProductService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProduct_Success() throws Exception {
        // Arrange
        ProductRequest request = new ProductRequest(1L, "Phone", "Smartphone", new BigDecimal("999.99"));
        ProductResponse response = new ProductResponse(1L, "Phone", "Smartphone", new BigDecimal("999.99"));

        Mockito.when(productService.createProduct(Mockito.any(ProductRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Phone"))
                .andExpect(jsonPath("$.description").value("Smartphone"))
                .andExpect(jsonPath("$.price").value(999.99));
    }
}
