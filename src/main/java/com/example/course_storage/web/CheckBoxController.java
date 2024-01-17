package com.example.course_storage.web;

import com.example.course_storage.domain.CheckBox;
import com.example.course_storage.domain.MoleculeDto;
import com.example.course_storage.service.MolecularService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Controller
@RequestMapping

public class CheckBoxController {
   // private final List<Long> check;
    private final MolecularService molecularService;
    @GetMapping("/check")
    public String check(@ModelAttribute(name = "check") CheckBox checkBox, Model model) {
        List<MoleculeDto> all = molecularService.getAll();
        model.addAttribute("mol", all);

        List<Long> checkedItems = checkBox.getCheckedItems();
        if(checkedItems != null) {
            model.addAttribute("test", checkedItems);
        }
        return "checkbox";
    }

    @PostMapping ("/check")
    public String processForm(@ModelAttribute(value="check") CheckBox checkBox, Model model) {
        // Get value of checked item.

        List<Long> checkedItems = checkBox.getCheckedItems();
        checkBox.setCheckedItems(checkedItems);
        for(Long s : checkedItems) {
            System.out.println(s);
        }
        if(checkedItems != null) {
            model.addAttribute("test", checkedItems);
        }
        List<MoleculeDto> all = molecularService.getAll();
        model.addAttribute("mol", all);

        return "checkbox";
    }
}
