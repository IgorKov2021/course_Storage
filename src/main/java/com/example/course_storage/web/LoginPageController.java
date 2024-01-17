package com.example.course_storage.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping()
public class LoginPageController {
    @GetMapping("/start")
    public ModelAndView start() {
        ModelAndView modelAndView = new ModelAndView("start");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication.isAuthenticated();

        Object principal = authentication.getPrincipal();
        Object details = authentication.getDetails();
        Object credentials = authentication.getCredentials();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String name = authentication.getName();
        if(name.equals("anonymousUser")) {
            authenticated = false;

        }
        modelAndView.addObject("auth", authenticated);
        modelAndView.addObject("user", name);
        return modelAndView;
    }
}
