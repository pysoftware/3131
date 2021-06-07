package ru.sazonov.future1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sazonov.future1.AbstractTest;
import ru.sazonov.future1.requests.LordModel;
import ru.sazonov.future1.services.LordService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class LordControllerTest extends AbstractTest {

    @Autowired
    private LordService lordService;

    @Test
    public void createLord() throws JsonProcessingException {
        LordModel lordModel = LordModel.builder().age(1L).name("name").build();
        String bodySendData = getObjectMapper().writeValueAsString(lordModel);

        given()
                .body(bodySendData)
                .contentType(ContentType.JSON)
                .post("/lords")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(200);
    }

    @Test
    public void updateLord() {
    }

    @Test
    public void getLordById() {
        LordModel lordModel = LordModel.builder().age(1L).name("name").build();
        String bodySendData = getObjectMapper().writeValueAsString(lordModel);

        given()
                .body(bodySendData)
                .contentType(ContentType.JSON)
                .post("/lords")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(200);
    }

    @Test
    public void findFirst10OrderByAgeDesc() {
    }

    @Test
    public void findAllSlackerLords() {
    }
}