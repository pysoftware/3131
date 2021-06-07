package ru.sazonov.future1.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sazonov.future1.AbstractTest;
import ru.sazonov.future1.enteties.Lord;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LordRepositoryTest extends AbstractTest {

    @Test
    void findFirst10Lords() {

        Lord lord = new Lord();
        lord.setAge(10L);
        lord.setName("name");
//        lord = entityManager.persistAndFlush(lord);
//        assertThat(lordRepository.findById(lord.getId()).get()).isEqualTo(lord);
    }

    @Test
    void findById() {
    }

    @Test
    void findAllSlackerLords() {
    }
}