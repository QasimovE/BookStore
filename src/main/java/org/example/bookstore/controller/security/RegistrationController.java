package org.example.bookstore.controller.security;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.dto.securityDtos.LoginForm;
import org.example.bookstore.model.MyUser;
import org.example.bookstore.repository.MyUserRepository;
import org.example.bookstore.service.securityServices.JwtService;
import org.example.bookstore.service.securityServices.MyUserDetailsService;
import org.example.bookstore.service.securityServices.RegistrationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private  final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;
    private  final MyUserDetailsService myUserDetailsService;

     @PostMapping("/register/user")
    public String createUser(@RequestBody MyUser user){
       return registrationService.createUser(user);
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username(),loginForm.password()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginForm.username()));
        } else{
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

}
