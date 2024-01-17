package com.example.course_storage.web;

import com.example.course_storage.domain.*;
import com.example.course_storage.exceptions.ExceptionNumber2;
import com.example.course_storage.service.GoodService;
import com.example.course_storage.service.MolecularService;
import com.example.course_storage.service.PatternService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping("")
public class PatternController {

    private final PatternService patternService;
    private final MolecularService molecularService;
    private final GoodService goodService;

    /*@GetMapping("/pattern")
    public ModelAndView addPattern(@ModelAttribute (name = "pattern") PatternDto patternDto,
                                   @ModelAttribute (name = "good") GoodDto goodDto,
                                   @ModelAttribute (name = "check") CheckBox checkBox,
                                   String keyword) {

        ModelAndView modelAndView = new ModelAndView("pattern");
        List<PatternDto> allPatterns = patternService.getAllPatterns();

        List<MoleculeDto> allMolecules = molecularService.getAll();
        modelAndView.addObject("allMolecules", allMolecules);
        List<GoodDto> allGoods = goodService.getAll();

        modelAndView.addObject("success", null );

        List<GoodDto> byKeyword = goodService.findByKeyword(keyword);
        modelAndView.addObject("allGoods", byKeyword);
        //modelAndView.addObject("companies", all);

        return modelAndView;
    }*/

    @GetMapping("/add/pattern")
    public ModelAndView addPattern(@ModelAttribute(name = "pattern") PatternDto patternDto,
                                   @ModelAttribute(name = "good") GoodDto goodDto,
                                   @ModelAttribute(name = "check") CheckBox checkBox,
                                   String keyword) {

        ModelAndView modelAndView = new ModelAndView("addPattern");

        List<MoleculeDto> allMolecules = molecularService.getAll();
        modelAndView.addObject("allMolecules", allMolecules);

        List<GoodDto> allGoods = goodService.getAll();
        modelAndView.addObject("goods", allGoods);

        modelAndView.addObject("success", null);

        List<GoodDto> byKeyword = goodService.findByKeyword(keyword);
        modelAndView.addObject("goods", byKeyword);

        List<Long> checkedItems = checkBox.getCheckedItems();
        if (checkedItems != null) {
            modelAndView.addObject("test", checkedItems);
        }

        return modelAndView;
    }

    @PostMapping("/add/pattern")
    public ModelAndView savePattern(@ModelAttribute(name = "pattern") PatternDto patternDto,
                                    @ModelAttribute(name = "good") GoodDto goodDto,
                                    @ModelAttribute(name = "check") CheckBox checkBox,
                                    @RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "action") String action) {


        ModelAndView modelAndView = new ModelAndView("addPattern");
        switch (action) {
            case "search":
                List<GoodDto> byKeyword = goodService.findByKeyword(keyword);
                modelAndView.addObject("goods", byKeyword);

                break;
            case "submit":

                patternService.savePattern(patternDto);

                ModelAndView modelAndView1 = new ModelAndView("showPattern");

                List<PatternDto> allPatterns = patternService.getAllPatterns();
                modelAndView1.addObject("patterns", allPatterns);
                modelAndView1.addObject("success", "Pattern " + patternDto.getNameOfPattern() + " was successfully created!");


                return modelAndView1;

            case "reset":
                byKeyword = goodService.findByKeyword(keyword);
                modelAndView.addObject("goods", byKeyword);
        }

        List<MoleculeDto> allMolecules = molecularService.getAll();
        modelAndView.addObject("allMolecules", allMolecules);

        List<Long> checkedItems = checkBox.getCheckedItems();
        if (checkedItems != null) {
            modelAndView.addObject("test", checkedItems);
        }


        return modelAndView;
    }

    @GetMapping("/show/pattern")
    public ModelAndView addPattern(@ModelAttribute(name = "patternAll") PatternDto patternDto) {

        ModelAndView modelAndView = new ModelAndView("showPattern");

        List<PatternDto> allPatterns = patternService.getAllPatterns();
        modelAndView.addObject("patterns", allPatterns);


        return modelAndView;
    }

    @PostMapping("/update/pattern")
    public ModelAndView updatePattern(
            @RequestParam(name = "id") Long id,
            @ModelAttribute(name = "check") CheckBox checkBox,
            @RequestParam(name = "action") String action,
            @RequestParam(name = "quantity" , required = false)  Integer quantity) {

        if(action.equals("Write-Off")) {
            patternService.writeOffGoods(id);
            ModelAndView modelAndView1 = new ModelAndView("showPattern");

            List<PatternDto> allPatterns = patternService.getAllPatterns();

            modelAndView1.addObject("patterns", allPatterns);

            return modelAndView1;


        }

        ModelAndView modelAndView = new ModelAndView("updatePattern");

        PatternDto byId = patternService.getById(id);
        modelAndView.addObject("pattern", byId);


        return modelAndView;
    }

   /* @GetMapping("/update/pattern/add")
    public ModelAndView updateAddPattern(@ModelAttribute(name = "patternAll") PatternDto patternDto,
                                         @RequestParam(name = "id") Long id,
                                         @ModelAttribute(name = "good") GoodDto goodDto,
                                         @ModelAttribute(name = "check") CheckBox checkBox) {

        ModelAndView modelAndView = new ModelAndView("addPatternUpdate");

        PatternDto byId = patternService.getById(id);
        modelAndView.addObject("pattern", byId);

        List<GoodDto> allGoods = goodService.getAll();
        modelAndView.addObject("goods", allGoods);


        List<Long> checkedItems = checkBox.getCheckedItems();
        if (checkedItems != null) {
            modelAndView.addObject("test", checkedItems);
        }

        return modelAndView;
    }*/

    @PostMapping("/action/update/pattern")
    public ModelAndView changePattern(@RequestParam(name = "action") String action,
                                      @RequestParam(name = "id") Long id,
                                      @ModelAttribute(name = "check") CheckBox checkBox,
                                      @ModelAttribute(name = "name") String patternName) {

        switch (action) {
            case "change products":
                ModelAndView modelAndView = new ModelAndView("addPatternUpdate");

                PatternDto byId = patternService.getById(id);
                modelAndView.addObject("pattern", byId);

                List<GoodDto> allGoods = goodService.getAll();
                modelAndView.addObject("goods", allGoods);


                List<Long> checkedItems = checkBox.getCheckedItems();
                if (checkedItems != null) {
                    modelAndView.addObject("test", checkedItems);
                }

                return modelAndView;



            case "delete pattern":
                ModelAndView modelAndView1 = new ModelAndView("showPattern");

                modelAndView1.addObject("success", "The pattern " + patternName + " was successfully deleted!");
                patternService.deletePattern(id);
                List<PatternDto> allPatterns = patternService.getAllPatterns();

                modelAndView1.addObject("patterns", allPatterns);

                return modelAndView1;

        }
     /*   PatternDto byId = patternService.getById(id);
        modelAndView.addObject("pattern", byId);
*/

        return new ModelAndView("about");
    }

    @PostMapping("/add/pattern/update")
    public ModelAndView savePattern(@ModelAttribute(name = "pattern") PatternDto patternDto,
                                    @RequestParam(name = "id") Long id,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "action") String action,
                                    @RequestParam(name = "keyword", required = false) String keyword,
                                    @ModelAttribute(name = "check") CheckBox checkBox) {

        ModelAndView modelAndView = new ModelAndView("showPattern");

        switch (action) {
            case "reset":
                ModelAndView modelAndView2 = new ModelAndView("addPatternUpdate");
                List<GoodDto> byKeyword = goodService.findByKeyword(keyword);
                modelAndView2.addObject("goods", byKeyword);

                List<Long> checkedItems = checkBox.getCheckedItems();
                if (checkedItems != null) {
                    modelAndView.addObject("test", checkedItems);
                }

                return modelAndView2;

                case "search":
                ModelAndView modelAndView1 = new ModelAndView("addPatternUpdate");
                byKeyword = goodService.findByKeyword(keyword);
                modelAndView1.addObject("goods", byKeyword);

                 checkedItems = checkBox.getCheckedItems();
                if (checkedItems != null) {
                    modelAndView.addObject("test", checkedItems);
                }

               return modelAndView1;

        }

        patternService.UpdatePatternProducts(id, patternDto);




        List<PatternDto> allPatterns = patternService.getAllPatterns();
        modelAndView.addObject("patterns", allPatterns);
        modelAndView.addObject("success", "Pattern " + name + " was successfully updated!");


        return modelAndView;

    }
}
