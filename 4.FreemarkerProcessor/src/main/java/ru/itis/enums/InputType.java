package ru.itis.enums;

public enum InputType {

    NUMBER("number"),
    TEXT("text"),
    EMAIL("email"),
    PASSWORD("password");

    private String value;

    InputType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
