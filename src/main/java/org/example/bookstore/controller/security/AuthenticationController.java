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

//    @PutMapping("/change")
//    public ResponseEntity<String> change(@RequestBody LoginForm registerForum){
//        boolean changed= authService.change(registerForum);
//        if(changed){
//            return ResponseEntity.ok("Password updated successfuly to "+ registerForum.password());
//        } else {
//            return ResponseEntity.status(400).body("Invalid username");
//        }
//    }

    @PostMapping("/login")
    public void authenticate(@RequestBody LoginForm form) throws UsernameNotFoundException{
       authService.authenticate(form);
    }


//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> loginResponseResponseEntity(@RequestBody LoginForm loginForm){
//        try {
//            return ResponseEntity.ok(authService.authenticate(loginForm));
//        } catch (UsernameNotFoundException e){
//            return ResponseEntity.status(401).body(new LoginResponse(e.getMessage(), null,0L));
//        }
//    }
}
