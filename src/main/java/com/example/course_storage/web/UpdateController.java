package com.example.course_storage.web;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.service.FirmService;
import com.example.course_storage.service.GoodService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor

public class UpdateController {

    private final GoodService goodService;
    private final FirmService firmService;


    /*@GetMapping("/update")
    public ModelAndView showDetail(@RequestParam(name = "id") UUID id) {

        GoodDto byId = goodService.getById(id);
        //List<FirmDto> allCompanies = firmService.getAll();
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("good", byId);
      //  modelAndView.addObject("allCompanies", allCompanies);

        return modelAndView;
    }*/

    @PostMapping("/update")
    public ModelAndView showDetailButton(@RequestParam(name = "id") UUID id) {

        GoodDto byId = goodService.getById(id);
        List<FirmDto> allCompanies = firmService.getAll();
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("good", byId);
        modelAndView.addObject("allCompanies", allCompanies);

        return modelAndView;
    }


    @PostMapping("/action")
    public String update(@RequestParam(name = "id") UUID id,
                         GoodDto dto,
                         @RequestParam(name = "action") String action) {

        if (action != null && action.equals("delete")) {
            goodService.delete(id);
        } else {
            goodService.update(id, dto);
        }

        return "redirect:/storage";
    }
}
