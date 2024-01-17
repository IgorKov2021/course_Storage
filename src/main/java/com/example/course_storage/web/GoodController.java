package com.example.course_storage.web;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.SearchGoodDto;
import com.example.course_storage.service.GoodService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Controller
@RequestMapping("")
public class GoodController {

    private final GoodService goodService;

    @GetMapping("/good")
    public ModelAndView showGood(@ModelAttribute(name = "good") GoodDto goodDto) {
        ModelAndView modelAndView = new ModelAndView("goods");
        List<GoodDto> all = goodService.getAll();
        modelAndView.addObject("goods", all);
        return modelAndView;
    }
    @GetMapping("/test")
    public ModelAndView showGood3(@ModelAttribute(name = "good") GoodDto goodDto) {
        ModelAndView modelAndView = new ModelAndView("test");
        //List<GoodDto> all = goodService.getAll();
        //modelAndView.addObject("goods", all);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean authenticated = authentication.isAuthenticated();
        Object credentials = authentication.getCredentials();
        Object details = authentication.getDetails();
        Object principal = authentication.getPrincipal();
        String name = authentication.getName();
        return modelAndView;
    }


   @PostMapping("/good")
    public ModelAndView saveGood(@ModelAttribute(name = "good") @Valid GoodDto goodDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("goods");
        if (!bindingResult.hasErrors()) {
            goodService.save(goodDto);
            List<GoodDto> all = goodService.getAll();
            modelAndView.addObject("goods", all);
        }
        List<GoodDto> all = goodService.getAll();
        modelAndView.addObject("goods", all);
        return modelAndView;
    }

    @PostMapping("/test")
    public String string() {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("test", "test");
        System.out.println("Sdsd");
        return "test";
    }
    @GetMapping("/detail")
    public ModelAndView showDetail(@RequestParam(name = "id") UUID id) {
        GoodDto byId = goodService.getById(id);
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("good", byId);
        return modelAndView;
    }

    @PostMapping("/detail")
    public ModelAndView showDetailButton(@RequestParam(name = "id") UUID id) {
        GoodDto byId = goodService.getById(id);
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("good", byId);
        return modelAndView;
    }

    /*@PostMapping("/action")
    public String update(@RequestParam(name = "id") UUID id,
                         GoodDto dto,
                         @RequestParam(name = "action") String action) {

        ModelAndView modelAndView = new ModelAndView("detail");
        if (action != null && action.equals("delete")) {
            goodService.delete(id);
        } else {
            goodService.update(id, dto);
        }
        return "redirect:/storage";
    }*/

    @GetMapping("/good/search")
    public ModelAndView search(@ModelAttribute(name = "searchGood") SearchGoodDto goodDto) {

        ModelAndView modelAndView = new ModelAndView("search");
        List<GoodDto> search = goodService.search(goodDto);
        modelAndView.addObject("goods", search);
        return modelAndView;
    }

    @GetMapping("/good/searchS")
    public ModelAndView searchSpecification(SearchGoodDto goodDto) {
        ModelAndView modelAndView = new ModelAndView("searchSpecification");
        List<GoodDto> find = goodService.searchSpecification(goodDto);
        modelAndView.addObject("findGoods", find);

        return modelAndView;
    }
    @PostMapping("/delete")
    public String saveGood(@RequestParam (name = "id" ) UUID id) {
        goodService.delete(id);


        return "redirect:/good";
    }


    @GetMapping("/test2")
    public ModelAndView test2(@RequestParam(name = "action") String str) {
        ModelAndView modelAndView = new ModelAndView("storage");
        String a = "sdsdsd";
        List<GoodDto> all = goodService.getAll();
        modelAndView.addObject("goods", all);
        modelAndView.addObject("show_all", a);
        return modelAndView;
    }
}
