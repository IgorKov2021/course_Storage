package com.example.course_storage.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@Controller
@RequestMapping("/listStudents")
public class StudentController {
/*
    private final StudentService studentService;

    @GetMapping
    public String listStudents(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);

        Page<Student> studentPage = studentService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("studentPage", studentPage);

        int totalPages = studentPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "listBooks";
    }*/
}

