package org.example.bookstore.controller.security;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.model.MyUser;
import org.example.bookstore.repository.MyUserRepository;
import org.example.bookstore.service.securityServices.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/register")
public class RegistrationController {
//    private final MyUserRepository myUserRepository;
//    private final PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;

//    @PostMapping("/register/user")
//    public MyUser createUser(@RequestBody MyUser user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return myUserRepository.save(user);
//    }
     @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user){
       return registrationService.createUser(user);
    }

}
