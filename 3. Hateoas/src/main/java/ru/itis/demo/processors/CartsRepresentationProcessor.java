package ru.itis.demo.processors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.demo.controllers.CartController;
import ru.itis.demo.controllers.OrderController;
import ru.itis.demo.models.Cart;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CartsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Cart>> {

    @Override
    public EntityModel<Cart> process(EntityModel<Cart> model) {
        Cart cart = model.getContent();
        model.add(
                linkTo(methodOn(CartController.class).deleteAll()).withRel("deleteAll"),
                Link.of("http://localhost:8080/carts/coupon", "applyCoupon")
        );
        if (!cart.getProducts().isEmpty()) {
            model.add(linkTo(methodOn(OrderController.class).doOrder()).withRel("doOrder"));
        }
        return model;
    }
}
