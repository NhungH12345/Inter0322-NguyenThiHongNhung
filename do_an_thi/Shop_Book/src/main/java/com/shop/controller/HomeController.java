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
    @GetMapping("")
//    public ModelAndView listProduct(@RequestParam(name = "nameP", required = false, defaultValue = "") String nameProduct, @PageableDefault(size = 2) Pageable pageable){
//        ModelAndView modelAndView = new ModelAndView("home");
////        Page<Employee> page = employeeService.findAll(pageable);
//        Page<Product> page = productService.searchByName(nameProduct, pageable);
//        long totalItems = page.getTotalElements();
//        modelAndView.addObject("currentPage",pageable.getPageNumber() + 1);
//        modelAndView.addObject("totalItems",totalItems);
//        modelAndView.addObject("nameP",nameProduct);
//        modelAndView.addObject("products",page);
//        return modelAndView;
    public ModelAndView getProductList(@PageableDefault(size = 1) Pageable pageable, @RequestParam("search") Optional<String> search) {
        Page<Product> products;
        if (search.isPresent()) {
            products = productService.findProductByNameContaining(search.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", products);
        return modelAndView;

    }

    @GetMapping("home")
    public ModelAndView getHome(@ModelAttribute("products") Product product){
        return new ModelAndView("home", "products",productService.findAll());
    }

//    @GetMapping("home")
//    public String GetHomePage() {
//        return "home";
//    }

    @GetMapping("/404")
    public String GetLoiPage() {
        return "404";
    }

    @GetMapping("/cart")
    public String GetCartPage() {
        return "cart";
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
            //Lưu đối tượng Authentication vào SecurityContext(giữ thông tin xác thực)
            SecurityContextHolder.getContext().setAuthentication(authentication);*/
            // hoặc cast đối tượng về đối tượng do ta định nghĩa như bên dưới (UserDetailsImpl implements UserDetails)
            // userDetail chứa thông tin để tạo đối tượng userDetails
            try {
                UserDetails userLogined = userDetailsService.loadUserByUsername(userDTO.getEmail());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
                Authentication authentication = authenticationManager.authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println(userLogined.getAuthorities());
                String jwt = jwtUtils.generateJwtToken(userLogined).trim();
                System.out.println(jwt);
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
            helper.setSubject("Xin chào quý khách");
            helper.setText(htmlBody, true);
            emailSender.send(messageCovert);
            userRepository.save(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return new ModelAndView("login");
    }
//    @GetMapping("/cart")
//    public ModelAndView getCartPage() {
//        return new ModelAndView("cart");
//    }

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

