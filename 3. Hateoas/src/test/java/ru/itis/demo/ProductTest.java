package ru.itis.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.demo.models.Cart;
import ru.itis.demo.models.Product;
import ru.itis.demo.models.ProductCategory;
import ru.itis.demo.repositories.ProductCategoryRepository;
import ru.itis.demo.services.CartService;

import java.util.LinkedList;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @MockBean
    private CartService cartService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void productAddToCartTest() throws Exception {
        when(cartService.addProductToCart(1L)).thenReturn(newProduct());
        mockMvc.perform(put("/products/1/addToCart")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newProduct().getName()))
                .andExpect(jsonPath("$.price").value(newProduct().getPrice()))
                .andExpect(jsonPath("$.description").value(newProduct().getDescription()))
                .andDo(document("product_addToCart", responseFields(
                        fieldWithPath("name").description("Навание продукта"),
                        fieldWithPath("price").description("Цена продукта"),
                        fieldWithPath("description").description("Описание продукта"),
                        fieldWithPath("_links.addToCart.href").description("Добавление товара в корзину"),
                        fieldWithPath("_links.deleteFromCart.href").description("Удаление товара из коризны")
                )));
    }

    private Product newProduct() {
        return Product.builder()
                .id(1L)
                .productCategory(ProductCategory.builder().id(1L).name("1").build())
                .description("1")
                .name("1")
                .price(1)
                .build();
    }

    @Test
    public void deleteProductFromCart() throws Exception {
        when(cartService.deleteProductFromCart(1L)).thenReturn(newProduct());
        mockMvc.perform(delete("/carts/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newProduct().getName()))
                .andExpect(jsonPath("$.price").value(newProduct().getPrice()))
                .andExpect(jsonPath("$.description").value(newProduct().getDescription()))
                .andDo(document("product_deleteFromCart", responseFields(
                        fieldWithPath("name").description("Навание продукта"),
                        fieldWithPath("price").description("Цена продукта"),
                        fieldWithPath("description").description("Описание продукта"),
                        fieldWithPath("_links.addToCart.href").description("Добавление товара в корзину"),
                        fieldWithPath("_links.deleteFromCart.href").description("Удаление товара из коризны")
                )));
    }



    private Cart emptyCart() {
        return Cart.builder()
                .price(0)
                .products(new LinkedList<>())
                .build();
    }
}
