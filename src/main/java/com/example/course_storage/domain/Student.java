package com.example.course_storage.domain;

import com.example.course_storage.model.Currency;
import com.example.course_storage.model.Price;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    /*    @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date birthDate;

        @Enumerated(EnumType.STRING)
        private Currency currency;*/

        //private Price price;

        private int id;
        private String name;


        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
}
