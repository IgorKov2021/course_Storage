package com.example.course_storage.exceptions;

import com.example.course_storage.domain.GoodDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ExceptionZeroProduct extends RuntimeException{





        private String message;
        private List<GoodDto> goodDto;


}
