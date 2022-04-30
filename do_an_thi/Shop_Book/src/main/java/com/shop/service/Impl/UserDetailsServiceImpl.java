package com.shop.service.Impl;
import com.shop.model.Role;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent() && user.get().getActivityKey() != null) {
            throw new UsernameNotFoundException("User not found");
        }
        else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            Set<Role> roles = user.get().getRoles();
            for (Role role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.get().getEmail(), user.get().getPassword(), grantedAuthorities);
        }




    }}
