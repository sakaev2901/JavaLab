package ru.itis;

import ru.itis.enums.FormMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface HtmlForm {
    FormMethod method() default FormMethod.GET;
    String action() default "/";
}
