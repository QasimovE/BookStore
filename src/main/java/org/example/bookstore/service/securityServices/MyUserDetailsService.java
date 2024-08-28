package org.example.bookstore.service.securityServices;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.model.MyUser;
import org.example.bookstore.repository.MyUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            MyUser myUser = user.get();
            return User.builder()
                    .username(myUser.getUsername())
                    .password(myUser.getPassword())
                    .roles(getRoles(myUser))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
    private String [] getRoles(MyUser user){
        if(user.getRole()==null){
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

//    public void createUser(UserDto form){
//        MyUser user = modelMapper.map(form, MyUser.class);
//        user.addRole(Role.USER);
//        userRepository.save(user);
//    }


}
