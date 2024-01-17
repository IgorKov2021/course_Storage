package com.example.course_storage.web;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.Student;
import com.example.course_storage.service.GoodService;
import com.example.course_storage.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sort")
public class SortController {

    private final GoodService goodService;
    private final StudentService studentService;
   /* @GetMapping("/{name}/{param}/storage")
    public ModelAndView addGood(
    @PathVariable String name,
    @PathVariable String param) {

        List<GoodDto> sorted = goodService.findAllBySort(name, param);
        ModelAndView modelAndView = new ModelAndView("storage");
        modelAndView.addObject("goods", sorted);


        return modelAndView;
    }*/

    @GetMapping ("")
    public ModelAndView sortAll(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "param") String param,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "size",required = false) Optional<Integer> size) {



            int currentPage = page.orElse(1);
            int pageSize = size.orElse(10);

            ModelAndView modelAndView = new ModelAndView("storage");
             List<GoodDto> sorted = goodService.findAllBySort(name, param);


            Page<GoodDto> goodPage = studentService.findPaginated(PageRequest.of(currentPage - 1, pageSize), sorted);

            modelAndView.addObject("goods", goodPage);

            int totalPages = goodPage.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                modelAndView.addObject("pageNumbers", pageNumbers);


            }


        modelAndView.addObject("nameSort", name);
        modelAndView.addObject("direction", param);



        return modelAndView;
    }


}

