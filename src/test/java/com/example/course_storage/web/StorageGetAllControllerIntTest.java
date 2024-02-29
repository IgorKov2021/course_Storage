package com.example.course_storage.web;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.GoodEntity;
import com.example.course_storage.repository.GoodRepository;
import com.example.course_storage.service.GoodService;
import com.example.course_storage.service.PageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StorageGetAllControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GoodRepository goodRepository;




    @Sql(value = "classpath:scripts/firm.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "classpath:scripts/goods.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "classpath:scripts/clean_all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void showAll() throws Exception {


        List<GoodEntity> all = goodRepository.findAll();



        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/storage"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Object goods = mvcResult.getModelAndView().getModel().get("goods");
        Page<GoodDto> goodPage = (Page<GoodDto>) goods;

        List<String> collect = goodPage.stream().map(goodDto -> goodDto.getName()).collect(Collectors.toList());



        Assertions.assertThat(collect).hasSize(2);
        Assertions.assertThat(collect).contains("Aceton");






    }
}