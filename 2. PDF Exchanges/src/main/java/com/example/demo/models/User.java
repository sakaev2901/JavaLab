package com.example.demo.models;

import com.example.demo.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String name;
    private String surname;
    private String patronymic;
    private String birthday;
    private String passportId;

    public static User from(String s) {
        String[] parts = s.split(" ");
        return User.builder()
                .name(parts[0])
                .surname(parts[1])
                .patronymic(parts[2])
                .birthday(parts[3])
                .passportId(parts[4])
                .build();
    }

    public static User from(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .patronymic(userDto.getPatronymic())
                .birthday(userDto.getBirthday())
                .passportId(userDto.getPassportId())
                .build();
    }
}
