package ru.sazonov.future1.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sazonov.future1.AbstractTest;
import ru.sazonov.future1.services.LordService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@Slf4j
class LordControllerTest extends AbstractTest {

    @Autowired
    private LordService lordService;

    @Test
    void createLord() {
//        WebMvc
        assertNotNull(planetRepository);
        log.info(String.valueOf(lordService));
    }

    @Test
    void updateLord() {
    }

    @Test
    void getLordById() {
    }

    @Test
    void findFirst10OrderByAgeDesc() {
    }

    @Test
    void findAllSlackerLords() {
    }
}