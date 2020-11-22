package ru.itis;

import ru.itis.enums.FormMethod;
import ru.itis.enums.InputType;

@HtmlForm(method = FormMethod.POST, action = "/users")
public class User {
    @HtmlField(name = "first_name", placeholder = "Имя")
    private String firstName;
    @HtmlField(name = "last_name", placeholder = "Фамилия")
    private String lastName;
    @HtmlField(name = "email", type = InputType.EMAIL, placeholder = "Почта")
    private String email;
    @HtmlField(name = "password", type = InputType.PASSWORD, placeholder = "Пароль")
    private String password;

}
