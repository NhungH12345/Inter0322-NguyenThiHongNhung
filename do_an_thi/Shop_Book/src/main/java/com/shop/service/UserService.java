package com.shop.service;


import com.shop.model.User;

public interface UserService {
    User save(User user);

    User findByEmail(String username);
}
