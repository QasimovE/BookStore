package org.example.bookstore.service.securityServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookstore.dto.securityDtos.LoginForm;
import org.example.bookstore.dto.securityDtos.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final PasswordEncoder passEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final MyUserDetailsService userService;

//    public boolean change(LoginForm dto){
//        UserDetails userDetails=userService.loadUserByUsername(dto.username());
//        String encoded=passEncoder.encode(dto.password());
//        if(userDetails!=null&&userDetails.getPassword().equals(encoded)){
//            inMemoryUserDetManager.updatePassword(userDetails,encoded);
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public LoginResponse register(UserDto dto){
//        dto.setPassword(passEncoder.encode(dto.getPassword()));
//        userService.createUser(dto);
//        String token=jwtService.generateToken(userService.loadUserByUsername(dto.getUsername()));
//        Long expiresIn=jwtService.getExpirationTime(token);
//        return new LoginResponse("success",token,expiresIn);
//
//    }



    public void authenticate(LoginForm form) throws UsernameNotFoundException{
        Authentication auth=authManager.authenticate(new UsernamePasswordAuthenticationToken(form.username(),form.password()));
        if(auth.isAuthenticated()){
            String token=jwtService.generateToken(userService.loadUserByUsername(form.username()));
            Long expiresIn=jwtService.getExpirationTime(token);
            new LoginResponse("success",token,expiresIn);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


}
