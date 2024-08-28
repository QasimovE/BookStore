package org.example.bookstore.service.securityServices;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.dto.securityDtos.LoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ContentService {
    private  final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;
    private  final MyUserDetailsService myUserDetailsService;

    public String authenticateAndGetToken(LoginForm loginForm){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username(),loginForm.password()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginForm.username()));
        } else{
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}
