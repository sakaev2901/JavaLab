package ru.itis;

import ru.itis.enums.FormMethod;
import ru.itis.enums.InputType;

@HtmlForm(action = "/foods", method = FormMethod.PUT)
public class Food {

    @HtmlField(placeholder = "Название продукта", name = "name")
    private String name;
    @HtmlField(placeholder = "Стоимость", name = "price", type = InputType.NUMBER)
    private Integer price;
}
