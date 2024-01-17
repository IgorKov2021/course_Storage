package com.example.course_storage.serviceImpl;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.mapper.FirmMapper;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.repository.FirmRepository;
import com.example.course_storage.service.FirmService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.Utilities;
import java.util.List;
@RequiredArgsConstructor

@Service
public class FirmServiceImpl implements FirmService {

    private final FirmRepository firmRepository;
    private final FirmMapper firmMapper;

    @Override
    public void save(FirmDto firmDto) {
        firmRepository.save(firmMapper.toFirmEntity(firmDto));
    }

    @Override
    public List<FirmDto> getAll() {
        List<FirmEntity> all = firmRepository.findAll();

            return firmMapper.toListFirmDto(all);

    }

    @Override
    public FirmEntity searchByCompanyName(String name) {
        if(StringUtils.isNotBlank(name)) {
            FirmEntity company = firmRepository.findByCompanyName(name);
        }
throw new RuntimeException();
    }
}
