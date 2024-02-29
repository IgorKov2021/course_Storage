package com.example.course_storage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class TestController {

    @GetMapping("/test1")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("b");
        return modelAndView;
    }
}
