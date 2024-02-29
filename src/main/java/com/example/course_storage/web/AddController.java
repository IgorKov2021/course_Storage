package com.example.course_storage.web;

import com.example.course_storage.domain.*;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.ImageEntity;
import com.example.course_storage.repository.FirmRepository;
import com.example.course_storage.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Controller
@RequestMapping("/add")
public class AddController {

    private final FirmService firmService;
    private final GoodService goodService;
    private final MolecularService molecularService;
    private final PersonService personService;
    private final ImageService imageService;

    @GetMapping("/company")
    public ModelAndView showAll(@ModelAttribute(name = "company") FirmDto firmDto) {
        ModelAndView modelAndView = new ModelAndView("company");

        List<FirmDto> all = firmService.getAll();
        modelAndView.addObject("success", null);



        return modelAndView;
    }

    @PostMapping("/company")
    public ModelAndView saveCompany(@ModelAttribute(name = "company") @Valid FirmDto firmDto,
                                    BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("company");

        if (!bindingResult.hasErrors()) {
            firmService.save(firmDto);
            List<FirmDto> all = firmService.getAll();
            // modelAndView.addObject("companies", all);
            modelAndView.addObject("success", "Information successfully saved!");

        }

        return modelAndView;
    }

    /* @GetMapping("/good")
     public ModelAndView addGood(@ModelAttribute(name = "goodNew") GoodDto goodDto
                                // @ModelAttribute(name = "company") FirmDto firmDto
                                 ) {

         ModelAndView modelAndView = new ModelAndView("good");
         List<FirmDto> allCompanies = firmService.getAll();
         modelAndView.addObject("allCompanies", allCompanies);
         //List<FirmDto> all = firmService.getAll();
         modelAndView.addObject("success", null );
         //modelAndView.addObject("companies", all);

         return modelAndView;
     }*/
    @GetMapping("/good")
    public ModelAndView addGood(@ModelAttribute(name = "good") GoodDto goodDto
                                // @ModelAttribute(name = "company") FirmDto firmDto
    ) {

        ModelAndView modelAndView = new ModelAndView("addGood");
        List<FirmDto> allCompanies = firmService.getAll();
        modelAndView.addObject("allCompanies", allCompanies);
        modelAndView.addObject("success", null);
        //modelAndView.addObject("localDate", LocalDate.now());

        return modelAndView;
    }

    @PostMapping("/good")
    public ModelAndView saveGood(@ModelAttribute(name = "good") @Valid GoodDto goodDto,
                                 @RequestParam("file") MultipartFile file,
                                 BindingResult bindingResult) throws IOException {
        String success;
        if (!bindingResult.hasErrors()) {
            if (!file.isEmpty()) {
                ImageEntity imageEntity = imageService.uploadImage(file);
                goodDto.setImages(imageEntity);

                goodService.save(goodDto);
                success = "Information successfully saved! ";
                success += "File uploaded successfully : " + file.getOriginalFilename();
            } else {
                goodService.save(goodDto);
                success = "Information successfully saved! ";
            }


        } else {
            success = "Error is happened, please, try again!" + bindingResult.getFieldError().getDefaultMessage();
        }

        ModelAndView modelAndView = new ModelAndView("addGood");
        List<FirmDto> allCompanies = firmService.getAll();
        modelAndView.addObject("allCompanies", allCompanies);
        modelAndView.addObject("success", success);
        modelAndView.addObject("good", new GoodDto());
        return modelAndView;
    }

    @GetMapping("/molecular")
    public ModelAndView addMolecular(@ModelAttribute(name = "molecular") MoleculeDto moleculeDto) {

        ModelAndView modelAndView = new ModelAndView("molecular");

        return modelAndView;
    }

    /*@PostMapping("/good")
    public ModelAndView saveGood(@ModelAttribute(name = "goodNew") @Valid GoodDto goodDto,
                                 //@ModelAttribute(name = "company") FirmDto firmDto
                                 //@RequestParam (name = "comp") String str,
                                 BindingResult bindingResult) {
        //FirmDto company = goodDto.getCompany();
        //String companyName1 = company.getCompanyName();
       // ModelAndView modelAndView = new ModelAndView("good");

        if (!bindingResult.hasErrors()) {
            //String companyName = goodDto.getCompany().getCompanyName();
            goodService.save(goodDto);
            //List<GoodDto> all = goodService.getAll();
            // modelAndView.addObject("companies", all);
            List<FirmDto> allCompanies = firmService.getAll();
           // modelAndView.addObject("success", "Information successfully saved!" );

            //modelAndView.addObject("allCompanies", allCompanies);

        }
        //ModelAndView modelAndView = new ModelAndView("good");
        List<FirmDto> allCompanies = firmService.getAll();
ModelAndView modelAndView = new ModelAndView("good");
        //return "redirect:/good";
        //return "redirect:/add/good";
        modelAndView.addObject("allCompanies", allCompanies);
        //List<FirmDto> all = firmService.getAll();
        modelAndView.addObject("success", "Information successfully saved! ");
        return modelAndView;
    }
    @GetMapping ("/molecular")
    public ModelAndView addMolecular(@ModelAttribute (name = "molecular") MoleculeDto moleculeDto) {

        ModelAndView modelAndView = new ModelAndView("molecular");

        return modelAndView;
    }*/

    @PostMapping("/molecular")
    public ModelAndView saveMolecular(@ModelAttribute(name = "molecular") MoleculeDto moleculeDto) {

        ModelAndView modelAndView = new ModelAndView("redirect:/add/pattern");
        molecularService.save(moleculeDto);
        //modelAndView.setViewName("pattern");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView addUser(@ModelAttribute(name = "user") PersonDto personDto) {

        ModelAndView modelAndView = new ModelAndView("addUser");


        return modelAndView;
    }

    @PostMapping("/user")
    public ModelAndView saveUser(@ModelAttribute(name = "user") PersonDto personDto) {


        String message = personService.save(personDto);
        if (message.equals("User successfully created. Please login!")) {
            ModelAndView modelAndView = new ModelAndView("start");
            modelAndView.addObject("message", message);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("addUser");
            modelAndView.addObject("message", message);
            return modelAndView;
        }


    }
}
