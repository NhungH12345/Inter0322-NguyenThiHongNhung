package com.shop.service.Impl;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return this.userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("not found username"));
    }
}
