package org.example.bookstore.dto.securityDtos;

public record LoginResponse(String result,String password,Long expiresIn) {
}
