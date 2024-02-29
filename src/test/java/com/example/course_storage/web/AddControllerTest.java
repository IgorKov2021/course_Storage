package com.example.course_storage.web;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AddController.class)

class AddControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirmService firmService;

    @MockBean
    private GoodService goodService;

    @MockBean
    private MolecularService molecularService;

    @MockBean
    private PersonService personService;

    @MockBean
    private ImageService imageService;

    @MockBean
    private PatternService patternService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void showAllCompanies() throws Exception {
        FirmDto firmDto = new FirmDto();
        String firm = objectMapper.writeValueAsString(firmDto);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/add/company")
                        .content(firm).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        ModelAndView modelAndView = resultActions.andReturn().getModelAndView();
        String viewName = modelAndView.getViewName();

        Assertions.assertThat("company").isEqualTo(viewName);

    }
}