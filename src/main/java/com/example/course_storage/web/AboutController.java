package com.example.course_storage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {
    @GetMapping("/about")
    public ModelAndView showAbout() {
        ModelAndView modelAndView = new ModelAndView("about");



        return modelAndView;
    }
}
