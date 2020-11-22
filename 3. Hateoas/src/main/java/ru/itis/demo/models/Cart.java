package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    @Column( nullable = false, columnDefinition = "int default 0")
    private Integer price = 0;
    @OneToOne // fixme: можно ли вытаскивать это без ссылки
    private Coupon coupon;

    public Cart() {
        products = new LinkedList<>();
    }
    public void setProducts(List<Product> products) {
        this.products = products;
        this.price = products.stream().reduce(0, (x,y) -> x + y.getPrice(), (x,y) -> x + y);
    }

    public void addProduct(Product product) { // FIXME: Может ли модель иметь бизнес логику
        if (products == null) {
            products = new LinkedList<>();
        }
        products.add(product);
        price += product.getPrice();
    }

}
