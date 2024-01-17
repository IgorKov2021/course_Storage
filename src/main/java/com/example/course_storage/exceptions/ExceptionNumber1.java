package com.example.course_storage.exceptions;

import com.example.course_storage.domain.GoodDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionNumber1 extends RuntimeException{



        private String message;
        //private GoodDto goodDto;
    }




