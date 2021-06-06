package ru.sazonov.future1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.sazonov.future1.AbstractTest;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.requests.LordModel;
import ru.sazonov.future1.services.LordService;

import java.util.*;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@Slf4j
public class LordControllerTest extends AbstractTest {

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
    public void Should_ReturnStatusCode200_When_GetLordByIdFoundInDb() {
        Planet planet1 = Planet.builder().id(1L).name("planet1").build();
        Planet planet2 = Planet.builder().id(2L).name("planet2").build();
        Set<Planet> planets = new HashSet<>(Arrays.asList(planet1, planet2));
        Lord lord = Lord.builder()
                .age(10L)
                .name("name")
                .planets(planets)
                .id(65L)
                .build();
        when(lordRepository.findById(65L)).thenReturn(Optional.of(lord));

        given()
                .get("/lords/65")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(200)
                .body("planets", notNullValue())
                .body("id", equalTo(65));
    }

    @Test
    public void Should_ReturnStatusCode404_When_GetLordByIdNotFound() {
        given()
                .get("/lords/65")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Lord with id: 65 not found"));
    }

    @Test
    public void Should_ReturnEmptyListOfLords_When_NoLordSlackersFound() {
        List<Lord> lords = new ArrayList<>();
        when(lordRepository.findAllSlackerLords()).thenReturn(lords);

        given()
                .get("/lords/slackers")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(200)
                .body("[0]", nullValue());
    }

    @Test
    public void Should_ReturnListOfLords_When_LordSlackersFound() {
        List<Lord> lords = new ArrayList<>(Collections.singletonList(Lord.builder().id(1L).build()));
        when(lordRepository.findAllSlackerLords()).thenReturn(lords);

        given()
                .get("/lords/slackers")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(200)
                .body("[0].id", equalTo(1));
    }

    @Test
    public void Should_Return10Lords_When_Get10Lords() {
        List<Lord> lords = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Lord lord = Lord.builder().id(i).build();
            lords.add(lord);
        }
        Page<Lord> page = new PageImpl<>(lords);

        when(lordRepository.findFirst10Lords(Pageable.ofSize(10))).thenReturn(page);

        given()
                .get("/lords")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(200)
                .body("[9].id", equalTo(10))
                .body("[10]", nullValue());
    }
}