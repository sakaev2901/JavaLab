package ru.itis;

public class InputDto {
    String placeholder;
    String name;
    String type;

    public InputDto(String placeholder, String name, String type) {
        this.placeholder = placeholder;
        this.name = name;
        this.type = type;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
