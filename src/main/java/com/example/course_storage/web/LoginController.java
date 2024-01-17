package com.example.course_storage.web;

import com.example.course_storage.domain.PersonDto;
import com.example.course_storage.service.PersonService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor

@Controller
@RequestMapping
public class LoginController {

    private final PersonService personService;

   /* @GetMapping("/login/good")
    public ModelAndView login (HttpServletRequest req, HttpServletRequest resp) {
        //String s = Arrays.stream(req.getCookies()).findFirst().map(cookie -> cookie.getValue()).orElse(null);
       // Cookie[] cookies1 = req.getCookies();

        ModelAndView modelAndView = new ModelAndView("login");
        //System.out.println(fooCookie);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication.isAuthenticated();
        String name = authentication.getName();
        modelAndView.addObject("auth", authenticated);
        modelAndView.addObject("user", name);
       // modelAndView.addObject("login", s);
        return modelAndView;
    }*/

    @GetMapping("/login/good")
    public ModelAndView method(@CurrentSecurityContext SecurityContext context) {
        String name = context.getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("login");
        boolean authenticated = false;
        if(name.equals("anonymousUser")) {
            authenticated = false;

        } else {
   authenticated = context.getAuthentication().isAuthenticated();
        }

        modelAndView.addObject("auth", authenticated);
        modelAndView.addObject("user", name);
        return modelAndView;
    }
    @PostMapping("/login/good")
    public ModelAndView loginUser (@ModelAttribute PersonDto personDto) {
        personService.save(personDto);

        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}