package com.shop.controller;

import com.shop.DTO.UserDTO;
import com.shop.config.JwtUtils;
import com.shop.model.Product;
import com.shop.model.ProductBrand;
import com.shop.model.Role;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import com.shop.service.ProductBrandService;
import com.shop.service.ProductService;
import com.shop.service.RoleService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;

//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductService productService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping(value = {"", "home"})
    public ModelAndView getProductList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "") String keyword) {
        Page<Product> products = productService.findProductByNameContaining(keyword, PageRequest.of(page - 1, 9));
        System.out.println(products.getTotalPages());
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", products);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("previousPage", page - 1);
        modelAndView.addObject("nextPage", page + 1);
        return modelAndView;

    }

    @GetMapping("/404")
    public String GetLoiPage() {
        return "404";
    }

    @GetMapping("/product-details")
    public String GetDetailPage() {
        return "product-details";
    }

    @GetMapping("/contact-us")
    public String GetContactPage() {
        return "contact-us";
    }

    @GetMapping("login")
    public ModelAndView getLoginPage(Model model) {
        model.addAttribute("userDTO",new UserDTO());
        return new ModelAndView("login");
    }
    @PostMapping("login")
    public ModelAndView postLoginPage(UserDTO userDTO, Model model) {
        if (userDTO != null) {
            /*Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails,userDetails.getAuthorities()));
            //L??u ?????i t?????ng Authentication v??o SecurityContext(gi??? th??ng tin x??c th???c)
            SecurityContextHolder.getContext().setAuthentication(authentication);*/
            // ho???c cast ?????i t?????ng v??? ?????i t?????ng do ta ?????nh ngh??a nh?? b??n d?????i (UserDetailsImpl implements UserDetails)
            // userDetail ch???a th??ng tin ????? t???o ?????i t?????ng userDetails
            try {
                UserDetails userLogined = userDetailsService.loadUserByUsername(userDTO.getEmail());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
                Authentication authentication = authenticationManager.authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(userLogined).trim();
            } catch (UsernameNotFoundException usernameNotFoundException) {
                model.addAttribute("error", "sai thong tin or tk chua acctive");
                return new ModelAndView("login");
            } catch (BadCredentialsException e) {
                model.addAttribute("error", "sai thong tin or tk chua acctive");
                return new ModelAndView("redirect:/");
            }
        }
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(Model model) {
        model.addAttribute("registry",new UserDTO());
        return new ModelAndView("register");
    }
    @Autowired
    UserRepository userRepository;
    @PostMapping ("/register")
    public ModelAndView postRegisterPage(UserDTO registry) {
//        SimpleMailMessage message = new SimpleMailMessage();

        //           message.setTo(registry.getEmail());
//        message.setSubject("alo");
        User user = new User();
        Role role = roleService.findByName("user");
        user.setEmail(registry.getEmail());
        user.setPassword(new BCryptPasswordEncoder(4).encode(registry.getPassword()));
        user.getRoles().add(role);
        String randomChar = RandomStringUtils.randomAlphabetic(10);
        user.setActivityKey(randomChar);
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("registry",registry);
        thymeleafContext.setVariable("user",user);
        String htmlBody = thymeleafTemplateEngine.process("GmailTemplate/RegisterTemplateGmail", thymeleafContext);

        // Send Message!
        MimeMessage messageCovert = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(messageCovert, true, "UTF-8");
            helper.setTo(registry.getEmail());
            helper.setSubject("Xin ch??o qu?? kh??ch");
            helper.setText(htmlBody, true);
            emailSender.send(messageCovert);
            userRepository.save(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return new ModelAndView("login");
    }

    @GetMapping("activty-accout/{key}")
    public ModelAndView activityAccount(@PathVariable(value = "key") String key){
        Optional<User> user = userRepository.findByActivityKey(key);
        if(user.isPresent()){
            return new ModelAndView("/404");
        }
        else {
            user.get().setActivityKey(null);
            userRepository.save(user.get());
        }

        return new ModelAndView("home");
    }
    @GetMapping("details/{id}")
    public ModelAndView getDetailsPage(@PathVariable Integer id, Model model) {
        Optional<Product> product = productService.findProductById(id);
        if (product != null) {
            return new ModelAndView("product-details", "products", product.get());
        } else {
            return new ModelAndView("/error");
        }

    }
}

