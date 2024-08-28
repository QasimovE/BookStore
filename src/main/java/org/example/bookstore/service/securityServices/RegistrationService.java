package org.example.bookstore.service.securityServices;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.model.MyUser;
import org.example.bookstore.repository.MyUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    public MyUser createUser(MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }
}
