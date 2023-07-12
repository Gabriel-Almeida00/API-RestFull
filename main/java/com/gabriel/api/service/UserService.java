package com.gabriel.api.service;

import com.gabriel.api.exceptions.ResourceNotFoundException;
import com.gabriel.api.model.User;
import com.gabriel.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var User = userRepository.findByUserName(username);
       if(username != null){
           return User;
       }else{
           throw new UsernameNotFoundException("UserName " + username + " not found!");
       }
    }
}
