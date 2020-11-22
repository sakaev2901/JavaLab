package com.example.demo.consumer.dtos;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public UserDto(String message) {
        String[] parts = message.split("-");
        fullName = parts[0];
        age = parts[1];
        birthday = parts[2];
        id = parts[3];
        issued = parts[4];
    }

    private String fullName;
    private String age;
    private String id;
    private String birthday;
    private String issued;
}
