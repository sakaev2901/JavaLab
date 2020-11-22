package com.example.demo.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String surname;
    private String patronymic;
    private String birthday;
    private String passportId;
    private String language;
}
