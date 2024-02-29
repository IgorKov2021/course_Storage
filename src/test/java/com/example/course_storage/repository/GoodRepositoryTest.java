package com.example.course_storage.repository;

import com.example.course_storage.model.GoodEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE,
        connection = EmbeddedDatabaseConnection.H2)

class GoodRepositoryTest {

    @Autowired
    GoodRepository goodRepository;

    @Test
    public void saveGood() {
        //give
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setName("Aceton");

        //when
        goodRepository.save(goodEntity);

        //that
        List<GoodEntity> all = goodRepository.findAll();
        System.out.println();
        org.assertj.core.api.Assertions.assertThat(all).hasSize(1);
        org.assertj.core.api.Assertions.assertThat(all.get(0).getName()).isEqualTo(goodEntity.getName());
    }

    @Test
    void searchByNameAndSerialNumber() {
        GoodEntity goodEntity1 = new GoodEntity();
        goodEntity1.setName("Aceton");
        goodEntity1.setSerialNumber("ref1");

        GoodEntity goodEntity2 = new GoodEntity();
        goodEntity2.setName("aceton");
        goodEntity2.setSerialNumber("ref1");

        goodRepository.save(goodEntity1);
        goodRepository.save(goodEntity2);

        List<GoodEntity> goodEntities = goodRepository.findByNameAndSerialNumber("Aceton", "ref1");

        org.assertj.core.api.Assertions.assertThat(goodEntities).hasSize(1);
        org.assertj.core.api.Assertions.assertThat(goodEntities.get(0).getSerialNumber()).isEqualTo("ref1");


    }
}