package com.project.java.project_work.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.java.project_work.model.User;
import com.project.java.project_work.repository.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Nessun utente trovato con questo Username");
        }
        
        return new DatabaseUserDetails(user.get());
    }

}
