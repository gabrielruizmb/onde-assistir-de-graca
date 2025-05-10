package com.example.demo.features.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        // User user = new User(
        //     null, 
        //     "ondeassistirdegraca@gmail.com", 
        //     new BCryptPasswordEncoder().encode("oadgsup3rs3cr3t"), 
        //     "Gabriel Ruiz Mussi Bersot", 
        //     "ROLE_ADMIN"
        // );
    
        // userRepository.save(user);
    }

    public void create() {
    }
}
