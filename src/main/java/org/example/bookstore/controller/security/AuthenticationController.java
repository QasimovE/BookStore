package org.example.bookstore.controller.security;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.dto.securityDtos.LoginForm;
import org.example.bookstore.dto.securityDtos.LoginResponse;
import org.example.bookstore.service.securityServices.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/login")
    public void authenticate(@RequestBody LoginForm form) throws UsernameNotFoundException{
       authService.authenticate(form);
    }
}
