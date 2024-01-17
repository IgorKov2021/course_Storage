package com.example.course_storage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CheckBox {
    private List<Long> checkedItems;

    public Boolean search(List<Long> list, UUID digit) {
        if (list != null) {
            boolean contains = list.contains(digit);
            return contains;
        }
        else return false;
    }
}
