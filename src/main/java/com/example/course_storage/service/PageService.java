package com.example.course_storage.service;

import com.example.course_storage.domain.GoodDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class PageService {



    public Page<GoodDto> findPaginated(Pageable pageable, List<GoodDto> goodDtos) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<GoodDto> list;

        if (goodDtos.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, goodDtos.size());
            list = goodDtos.subList(startItem, toIndex);
        }

        Page<GoodDto> bookPage
                = new PageImpl<GoodDto>(list, PageRequest.of(currentPage, pageSize), goodDtos.size());

        return bookPage;
    }

}
