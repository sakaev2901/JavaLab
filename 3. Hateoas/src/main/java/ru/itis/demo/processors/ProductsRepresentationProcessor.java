package ru.itis.demo.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.demo.controllers.CartController;
import ru.itis.demo.controllers.ProductController;
import ru.itis.demo.models.Cart;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.CartRepository;

import java.util.LinkedList;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Product>> {


    @Override
    public EntityModel<Product> process(EntityModel<Product> model) {
        Product product = model.getContent();
        model.add(
                linkTo(methodOn(ProductController.class).addToCart(product.getId())).withRel("addToCart"),
                linkTo(methodOn(CartController.class).deleteProduct(product.getId())).withRel("deleteFromCart")
        );
        return model;
    }
}
