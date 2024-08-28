package org.example.bookstore.dto.securityDtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private int age;
}
