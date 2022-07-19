package com.example.springwebtraining.service;

import com.example.springwebtraining.error.ApiException;
import com.example.springwebtraining.error.ErrorCode;
import com.example.springwebtraining.model.DUser;
import com.example.springwebtraining.repository.DUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DUserDetailsService implements UserDetailsService {

    @Autowired
    private DUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DUser dbUser = userRepository.findByEmail(email).orElseThrow(() -> new ApiException(ErrorCode.UNAUTHORIZED));
        return new org.springframework.security.core.userdetails.User(
                email,
                dbUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
