package ru.itis;

import ru.itis.enums.InputType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface HtmlField {
    String name() default "";
    InputType type() default InputType.TEXT;
    String placeholder() default "";

}
