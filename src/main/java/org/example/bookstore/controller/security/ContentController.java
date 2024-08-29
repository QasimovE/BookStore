package org.example.bookstore.controller.security;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.bookstore.dto.securityDtos.LoginForm;
import org.example.bookstore.service.securityServices.ContentService;
import org.example.bookstore.service.securityServices.MyUserDetailsService;
import org.example.bookstore.service.securityServices.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ContentController {
    private  final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;
    private  final MyUserDetailsService myUserDetailsService;
    private final ContentService contentService;

    @GetMapping("/home")
    public String handleWelcome(){
        return "Welcome to home!";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome(){
        return "Welcome to ADMIN home!";
    }

    @GetMapping("/user/home")
    public String handleUserHome(){
        return "Welcome to USER home!";
    }

//    @PostMapping("/authenticate")
//    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
//        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username(),loginForm.password()));
//        if(authentication.isAuthenticated()){
//            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginForm.username()));
//        } else{
//            throw new UsernameNotFoundException("Invalid credentials");
//        }
//    }

//    @PostMapping("/authenticate")
//    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
//        return contentService.authenticateAndGetToken(loginForm);
//    }
}
