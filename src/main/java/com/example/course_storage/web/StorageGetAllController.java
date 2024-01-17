package com.example.course_storage.web;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.Student;
import com.example.course_storage.service.GoodService;
import com.example.course_storage.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor

@Controller
@RequestMapping("/storage")
public class StorageGetAllController {

    private final GoodService goodService;

    /*@GetMapping
    public ModelAndView showAll(@ModelAttribute(name = "good") GoodDto goodDto) {

        ModelAndView modelAndView = new ModelAndView("storage");
        List<GoodDto> all = goodService.getAll();
        modelAndView.addObject("goods", all);


        return modelAndView;
    }*/


    private final StudentService studentService;

    @GetMapping
    public ModelAndView showAll(@ModelAttribute(name = "good") GoodDto goodDto,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {


        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        ModelAndView modelAndView = new ModelAndView("storage");
        List<GoodDto> all = goodService.getAll();
       // modelAndView.addObject("goods", all);


        Page<GoodDto> goodPage = studentService.findPaginated(PageRequest.of(currentPage - 1, pageSize), all);

        modelAndView.addObject("goods", goodPage);

        int totalPages = goodPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);


        }
        return modelAndView;
    }
}
