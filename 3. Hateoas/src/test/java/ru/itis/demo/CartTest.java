package ru.itis.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.demo.models.Cart;
import ru.itis.demo.models.Coupon;
import ru.itis.demo.models.ProductCategory;
import ru.itis.demo.services.CartService;

import java.util.LinkedList;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CartTest {


    @MockBean
    private CartService cartService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteAllProductsFromCartTest() throws Exception {
        when(cartService.deleteAllProductsFromCart()).thenReturn(emptyCart());
        mockMvc.perform(delete("/carts/deleteAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(emptyCart().getPrice()))
//                .andExpect(jsonPath("$.products").value(emptyCart().getProducts()))
                .andDo(document("cart_deleteAll", responseFields(
                        fieldWithPath("price").description("Общая стоимость корзины"),
                        fieldWithPath("_links.deleteAll.href").description("Удалить все товары с корзины"),
                        fieldWithPath("_links.applyCoupon.href").description("Использовать купон для корзины")
//                        fieldWithPath("products").description("Все товары корзины")
                )));
    }

    private Cart emptyCart() {
        return Cart.builder()
                .price(0)
                .products(new LinkedList<>())
                .build();
    }

    @Test
    public void applyCouponTest() throws Exception {
        when(cartService.applyCoupon("HELLO")).thenReturn(appliedCoupon());
        mockMvc.perform(put("/carts/coupon")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"code\": \"HELLO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(appliedCoupon().getName()))
                .andExpect(jsonPath("$.description").value(appliedCoupon().getDescription()))
                .andExpect(jsonPath("$.code").value(appliedCoupon().getCode()))
                .andExpect(jsonPath("$.percent").value(appliedCoupon().getPercent()))

                .andDo(document("cart_coupon", responseFields(
                        fieldWithPath("name").description("Название купона"),
                        fieldWithPath("description").description("Описание купона"),
                        fieldWithPath("percent").description("Процент купона"),
                        fieldWithPath("code").description("Кодовое слово купона")
                )))
        ;
    }

    private Coupon appliedCoupon() {
        return Coupon.builder()
                .code("HELLO")
                .description("Coupon description")
                .name("Coupon name")
                .percent(20)
                .productCategory(ProductCategory.builder()
                        .id(1L)
                        .name("Food")
                        .build())
                .build();
    }
}
