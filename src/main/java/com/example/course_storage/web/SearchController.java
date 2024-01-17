package com.example.course_storage.web;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.SearchGoodDto;
import com.example.course_storage.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor

@RequestMapping("/search")

public class SearchController {

    private final GoodService goodService;

    @GetMapping("/goods")
    ModelAndView searchPage(SearchGoodDto goodDto) {
        ModelAndView modelAndView = new ModelAndView("searchGoods");

        return modelAndView;
    }


    @PostMapping("/goods")
    ModelAndView searchGoods(SearchGoodDto goodDto) {

        ModelAndView modelAndView = new ModelAndView("searchGoods");
        List<GoodDto> searchGoods = goodService.searchSpecification(goodDto);
        modelAndView.addObject("goods", searchGoods);

        return modelAndView;
    }
}

