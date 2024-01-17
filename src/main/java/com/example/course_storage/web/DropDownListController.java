package com.example.course_storage.web;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.service.GoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping
public class DropDownListController {

    private final GoodService goodService;

    @GetMapping("/list")
    public ModelAndView dropDownList(@ModelAttribute(name = "good") GoodDto goodDto) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<GoodDto> all = goodService.getAll();
       modelAndView.addObject("options", all);
        return modelAndView;
    }
    @PostMapping ("/list")
    public ModelAndView showChosenName(@RequestParam(name = "name") String name, @ModelAttribute(name = "good")  GoodDto goodDto) {
        ModelAndView modelAndView = new ModelAndView("list");

        modelAndView.addObject("namechoose", name);
        return modelAndView;
    }
    @GetMapping("/list2")
    public ModelAndView dropDownList2() {
        ModelAndView modelAndView = new ModelAndView("list2");
        List<GoodDto> all = goodService.getAll();
        modelAndView.addObject("options", all);
        return modelAndView;
    }
    @PostMapping ("/list2")
    public ModelAndView showChosenName2(@RequestParam(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("list2");

        modelAndView.addObject("namechoose", name);
        return modelAndView;
    }
}
