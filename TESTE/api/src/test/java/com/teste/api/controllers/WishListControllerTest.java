package com.teste.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.teste.api.domain.WishProduct;
import com.teste.api.mocks.domain.WishProductFactory;
import com.teste.api.repository.WishProductRepository;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishProductRepository wishProductRepository;

    @BeforeEach
    public void setup() {
        when(wishProductRepository.save(any()))
                .thenAnswer(invocation -> {
                    WishProduct wishProduct = invocation.getArgument(0);
                    if(wishProduct.getUserId() == 2L) {
                        throw new RuntimeException();
                    }
                    return WishProductFactory.createValidWishProduct();
                });


        when(wishProductRepository.findAllByUserId(anyLong()))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    if(id == 2L) {
                        throw new RuntimeException();
                    }
                    return Arrays.asList(WishProductFactory.createValidWishProduct(),
                            WishProductFactory.createValidWishProductWithDifferentProduct());
                });

        when(wishProductRepository.findByUserIdAndProductCode(anyLong(), anyString()))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    String productCode = invocation.getArgument(1);
                    if(id == 2L && productCode.equals("123")) {
                        return Optional.empty();
                    }
                    return Optional.of(WishProductFactory.createValidWishProduct());
                });
    }

    @Test
    @DisplayName("Should add product to wish list successfully and return 201 created")
    void shouldAddProductToWishListSuccessfullyAndReturn201Created() {
        try {
                String json = "{\"user_id\": 1, \"product_code\": \"123\"}";
             mockMvc.perform(post("/wishlist/add")
                            .contentType(MediaType.APPLICATION_JSON)
                             .content(json.getBytes()))
                    .andExpect(status().isCreated())
                    .andReturn();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Should try to add product to wish list and return 400 bad request")
    void shouldTryToAddProductToWishListAndReturn400BadRequest() {
        try {
            String json = "{\"user_id\": 2, \"product_code\": \"123\"}";
            mockMvc.perform(post("/wishlist/add")
                            .contentType(MediaType.APPLICATION_JSON).content(json.getBytes()))
                    .andExpect(status().isConflict())
                    .andReturn();

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Should try to remove product to wish list and return 204 no content")
    void shouldTryToRemoveProductToWishListAndReturn204NoContent() {
        try {
            mockMvc.perform(delete("/wishlist/remove?userId=1&productCode=123")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andReturn();

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Should try to remove product to wish list and return 404 not found")
    void shouldTryToRemoveProductToWishListAndReturn404NotFound() {
        try {
            mockMvc.perform(delete("/wishlist/remove?userId=2&productCode=123")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Should try to get a user wish list and return a array with products codes")
    void shouldTryToGetAUserWishListAndReturnAArrayWithProductsCodes() {
        try {
            MvcResult mvcResult = mockMvc.perform(get("/wishlist/list?id=123")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
            assertEquals("[\"123\",\"456\"]", mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @DisplayName("Should try to get a user wish list and return 404 not found")
    void shouldTryToGetAUserWishListAndReturn404NotFound() {
        try {
            mockMvc.perform(get("/wishlist/list?id=2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();
        } catch (Exception e) {
            fail();
        }
    }
}
