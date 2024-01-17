package com.example.course_storage.serviceImpl;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.SearchGoodDto;
import com.example.course_storage.mapper.GoodMapper;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.GoodEntity;
import com.example.course_storage.repository.FirmRepository;
import com.example.course_storage.repository.GoodRepository;
import com.example.course_storage.service.GoodService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Service
public class GoodServiceImpl implements GoodService {

    private final GoodMapper goodMapper;
    private final GoodRepository goodRepository;
    private final FirmRepository firmRepository;

    @Transactional
    @Override
    public void save(GoodDto goodDto) {
        GoodEntity goodEntity = goodMapper.toGoodEntity(goodDto);
        goodRepository.save(goodEntity);
    }

    @Transactional
    //@Override
    /*public void save(GoodDto goodDto, String name) {
        FirmEntity byCompanyName = firmRepository.findByCompanyName(name);
        goodDto.setCompany(byCompanyName);
        GoodEntity save = goodRepository.save(goodMapper.toGoodEntity(goodDto));
        save.setCompany(byCompanyName);
    }*/

    @Override
    public List<GoodDto> getAll() {
        List<GoodEntity> all = goodRepository.findAll();
        return goodMapper.toListGoodDto(all);

    }

    @Override
    public GoodDto getById(UUID id) {
        GoodEntity goodById = goodRepository.getReferenceById(id);
        if (goodById != null) {
            GoodDto goodDto = goodMapper.toGoodDto(goodById);
            return goodDto;
        } else {
            throw new RuntimeException();
        }

    }

    @Transactional
    @Override
    public void update(UUID id, GoodDto dto) {
        GoodEntity referenceById = goodRepository.getReferenceById(id);
        updateAll(referenceById, dto);
        //goodRepository.save(referenceById);


    }

    @Override
    public List<GoodDto> search(SearchGoodDto searchGoodDto) {
        if (StringUtils.isNotBlank(searchGoodDto.getName()) && StringUtils.isNotBlank(searchGoodDto.getSerialNumber())) {
            List<GoodEntity> goodEntities = goodRepository.searchByNameAndSerialNumber(searchGoodDto.getName(), searchGoodDto.getSerialNumber());
            List<GoodDto> listGoodDto = goodMapper.toListGoodDto(goodEntities);
            return listGoodDto;
        } else {
            List<GoodEntity> all = goodRepository.findAll();
            return goodMapper.toListGoodDto(all);
        }
    }

    private Specification<GoodEntity> searchSpecificationCreate(SearchGoodDto searchGoodDto) {
        return (root, query, criteriaBuilder) ->

        {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(searchGoodDto.getName())) {
                Predicate name = criteriaBuilder.equal(root.get("name"), searchGoodDto.getName());
                predicates.add(name);
            }

            String serialNumber = searchGoodDto.getSerialNumber();

            if (StringUtils.isNotBlank(serialNumber)) {
                Predicate serialNum = criteriaBuilder.equal(root.get("serialNumber"), serialNumber);
                predicates.add(serialNum);
            }

            if (StringUtils.isNotBlank(searchGoodDto.getDescription())) {
                Predicate description = criteriaBuilder.equal(root.get("description"), searchGoodDto.getDescription());
                predicates.add(description);
            }

            if (StringUtils.isNotBlank(searchGoodDto.getInternalCode())) {
                Predicate internalCode = criteriaBuilder.equal(root.get("internalCode"), searchGoodDto.getInternalCode());
                predicates.add(internalCode);
            }
     /*    if (searchGoodDto.getCompany() != null) {
                Predicate company = criteriaBuilder.equal(root.get("company"), searchGoodDto.getCompany().getCompanyName());
                predicates.add(company);
            }*/

            if (searchGoodDto.getDateOfExpire() != null) {
                Predicate dateOfExpire = criteriaBuilder.equal(root.get("dateOfExpire"), searchGoodDto.getDateOfExpire());
                predicates.add(dateOfExpire);
            }

            if (searchGoodDto.getDateOfReceiving() != null) {
                Predicate dateOfReceiving = criteriaBuilder.equal(root.get("dateOfReceiving"), searchGoodDto.getDateOfReceiving());
                predicates.add(dateOfReceiving);
            }

            /*if (searchGoodDto.isCertificatePresent()) {
                Predicate certificate = criteriaBuilder.equal(root.get("certificate"), searchGoodDto.getIsCertificatePresent());
                predicates.add(certificate);
            }*/

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<GoodDto> searchSpecification(SearchGoodDto searchGoodDto) {
        List<GoodEntity> all = goodRepository.findAll(searchSpecificationCreate(searchGoodDto));
        return goodMapper.toListGoodDto(all);
    }

    //native query
    @Transactional
    @Override
    public void delete(UUID id) {
        goodRepository.deleteByIdMy(id);
    }

    @Override
    public List<GoodDto> findByKeyword(String keyword) {
        if (keyword != null && StringUtils.isNotBlank(keyword)) {
            return goodMapper.toListGoodDto(goodRepository.findByKeyword(keyword));
        } else {
            return getAll();
        }

    }

    @Override
    public List<GoodDto> findAllBySort(String param, String prop) {

        List<GoodEntity> models;

        if (prop.equals("asc")) {
            models = goodRepository.findAll(Sort.by(Sort.Direction.ASC, param));
        } else {
            models = goodRepository.findAll(Sort.by(Sort.Direction.DESC, param));
        }

        List<GoodDto> listGoodDto = goodMapper.toListGoodDto(models);

        return listGoodDto;
    }

    private GoodEntity updateAll(GoodEntity entity, GoodDto goodDto) {

        entity.setName(goodDto.getName());
        entity.setCompany(goodDto.getCompany());
        entity.setCertificatePresent(goodDto.isCertificatePresent());
        entity.setDateOfExpire(goodDto.getDateOfExpire());
        entity.setDateOfReceiving(goodDto.getDateOfReceiving());
        entity.setInternalCode(goodDto.getInternalCode());
        entity.setSerialNumber(goodDto.getSerialNumber());
        entity.setDescription(goodDto.getDescription());
        entity.setPrice(goodDto.getPrice());
        entity.setQuantity(goodDto.getQuantity());
        return entity;
    }
}
