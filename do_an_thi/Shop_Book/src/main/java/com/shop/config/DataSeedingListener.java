//package com.shop.config;
//
//import java.util.HashSet;
//
//import com.shop.ultil.EncrypPasswordUtils;
//import com.shop.repository.RoleRepository;
//import com.shop.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import com.shop.model.Role;
//import com.shop.model.User;
//
//@Component
//public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent arg0) {
//        // Roles
//        if (roleRepository.findByName("ROLE_ADMIN") == null) {
//            roleRepository.save(new Role("ROLE_ADMIN"));
//        }
//
//        if (roleRepository.findByName("ROLE_MEMBER") == null) {
//            roleRepository.save(new Role("ROLE_MEMBER"));
//        }
//
//        // Admin account
//        if (userRepository.findByUsername("thao") == null) {
//            User admin = new User();
//            admin.setUsername("thao");
//            admin.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
//
//            HashSet<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByName("ROLE_ADMIN"));
//            roles.add(roleRepository.findByName("ROLE_MEMBER"));
//            admin.setRoles(roles);
//            userRepository.save(admin);
//        }
//
//        // Member account
//        if (userRepository.findByUsername("thaopham") == null) {
//            User user = new User();
//            user.setUsername("thaopham");
//            user.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
//
//            HashSet<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByName("ROLE_MEMBER"));
//            user.setRoles(roles);
//            userRepository.save(user);
//        }
//    }
//
//}