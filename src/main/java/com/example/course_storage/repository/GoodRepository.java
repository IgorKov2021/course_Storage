package com.example.course_storage.repository;

import com.example.course_storage.model.GoodEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GoodRepository extends JpaRepository<GoodEntity, UUID>, JpaSpecificationExecutor<GoodEntity> {

    List<GoodEntity> findByNameAndSerialNumber(String name, String serial);


    /*@Modifying
    @Query(value = "delete GoodEntity g where g.id = :id")
    void deleteByIdMy(UUID id);*/
    //List<GoodEntity> findAllByOrderByNameAsc();
    @Modifying
    @Query(value = "delete from goods as g where g.id = :id", nativeQuery = true)
    void deleteByIdMy(UUID id);

    @Query(value = "select * from goods g where g.name like %:keyword% or g.serial_number like %:keyword%", nativeQuery = true)
    List<GoodEntity> findByKeyword(@Param("keyword") String keyword);

    //для сорттровки
    List<GoodEntity> findAll(Sort sort);


    /*@Query(value = "select * from goods g order by  g.name = :name ASC", nativeQuery = true)
    List<GoodEntity> findByKeywords(@Param("name") String keyword1);*/

}
