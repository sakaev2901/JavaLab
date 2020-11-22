package ru.itis.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.demo.models.Order;
import ru.itis.demo.models.Product;
import ru.itis.demo.services.OrderService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class OrderTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;
    private final Date date = new Date();


    @Test
    public void doOrderTest() throws Exception {
        String date = objectMapper.writeValueAsString(newOrder().getDate());
        date = date.substring(1, date.length() - 1);
        when(orderService.doOrder()).thenReturn(newOrder());
        mockMvc.perform(post("/orders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(newOrder().getPrice()))
                .andExpect(jsonPath("$.date").value(date))
                .andDo(document("orders", responseFields(
                        fieldWithPath("price").description("Стоимость заказа"),
                        fieldWithPath("date").description("Дата оформления заказа")
                )))
        ;
    }

    private Order newOrder() {
        List<Product> products = new LinkedList<>();
        products.add(Product.builder().name("1").price(1).build());
        return Order.builder()
                .price(1)
                .products(products)
                .date(this.date)
                .build();
    }

}
