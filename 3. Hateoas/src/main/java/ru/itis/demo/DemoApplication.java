package ru.itis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.demo.models.*;
import ru.itis.demo.repositories.*;
import ru.itis.demo.services.CartService;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

//        UserRepository userRepository = context.getBean(UserRepository.class);
//        ProductRepository productRepository = context.getBean(ProductRepository.class);
//        CartService cartService = context.getBean(CartService.class);
//        CouponRepository couponRepository = context.getBean(CouponRepository.class);
//        CartRepository cartRepository = context.getBean(CartRepository.class);
//
        ProductCategoryRepository productCategoryRepository = context.getBean(ProductCategoryRepository.class);
        productCategoryRepository.save(ProductCategory.builder().id(1L).name("1").build());

//        ProductCategory smartphoneCategory = ProductCategory.builder()
//                .name("Smartphones")
//                .build();
//        smartphoneCategory = productCategoryRepository.save(smartphoneCategory);
//        ProductCategory foodCategory = ProductCategory.builder()
//                .name("Foods")
//                .build();
//        foodCategory = productCategoryRepository.save(foodCategory);
//
//        User user = User.builder()
//                .username("sakaev")
//                .build();
//        userRepository.save(user);
//
//        Cart cart = Cart.builder()
//                .user(user)
//                .price(0)
//                .build();
//        cartRepository.save(cart);
//
//
//        Product product1 = Product.builder()
//                .name("Iphone 12 mini")
//                .description("Very tiny, light and expensive thing")
//                .price(69990)
//                .productCategory(smartphoneCategory)
//                .build();
//        productRepository.save(product1);
//
//        Product product = Product.builder()
//                .name("Pizza")
//                .description("Very hot, tasty and cheezy thing")
//                .price(799)
//                .productCategory(foodCategory)
//                .build();
//        productRepository.save(product);
//
//        Coupon coupon = Coupon.builder()
//                .code("Hello")
//                .description("Good coupon")
//                .name("New Coupon")
//                .percent(20)
//                .build();
//
//        Coupon coupon2 = Coupon.builder()
//                .code("Iphone")
//                .description("Good coupon")
//                .name("SALE IPHONE")
//                .percent(30)
//                .productCategory(smartphoneCategory)
//                .build();
//
//
//        couponRepository.save(coupon);
//        couponRepository.save(coupon2);
    }


}
