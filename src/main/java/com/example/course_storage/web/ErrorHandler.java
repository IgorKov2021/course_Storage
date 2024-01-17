package com.example.course_storage.web;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.PatternDto;
import com.example.course_storage.exceptions.ExceptionNumber1;
import com.example.course_storage.exceptions.ExceptionNumber2;
import com.example.course_storage.exceptions.ExceptionZeroProduct;
import com.example.course_storage.model.Currency;
import com.example.course_storage.model.Price;
import com.example.course_storage.service.FirmService;
import com.example.course_storage.service.GoodService;
import com.example.course_storage.service.PatternService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@ControllerAdvice
public class ErrorHandler {

    private final FirmService firmService;
    private final GoodService goodService;
    private final PatternService patternService;

        @ExceptionHandler(ExceptionNumber1.class)
        public ModelAndView errorPage(ExceptionNumber1 exception) {
          /*  ModelAndView modelAndView = new ModelAndView("a");
           String message = exception.getMessage();
           modelAndView.addObject("message", message);
            return modelAndView;*/

            ModelAndView modelAndView = new ModelAndView("addGood");
            List<FirmDto> allCompanies = firmService.getAll();
            modelAndView.addObject("allCompanies", allCompanies);
            GoodDto goodDto = new GoodDto();
            //goodDto.setPrice(new Price(1, Currency.BYN));
            goodDto.setPrice(new Price());
            modelAndView.addObject("good", goodDto);
            modelAndView.addObject("success", null);
            String message = exception.getMessage();
            modelAndView.addObject("message", message);
            return modelAndView;
        }

    @ExceptionHandler(ExceptionNumber2.class)
    public ModelAndView errorPage2(ExceptionNumber2 exception) {
            ModelAndView modelAndView = new ModelAndView("exception");
       String exceptionMsg = "Pattern for this molecule is already exist!";
        modelAndView.addObject("exception", exceptionMsg);
            return modelAndView;
    }

    @ExceptionHandler(ExceptionZeroProduct.class)
    public ModelAndView showZeroProducts(ExceptionZeroProduct exception) {
        String message = exception.getMessage();
        List<GoodDto> goodDto = exception.getGoodDto();


        ModelAndView modelAndView = new ModelAndView("showPattern");

        List<PatternDto> allPatterns = patternService.getAllPatterns();
        modelAndView.addObject("patterns", allPatterns);
        modelAndView.addObject("error", message);
        modelAndView.addObject("goods", goodDto);


        return modelAndView;
    }
    }


