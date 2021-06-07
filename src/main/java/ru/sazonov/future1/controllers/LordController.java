package ru.sazonov.future1.controllers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.requests.LordModel;
import ru.sazonov.future1.services.LordService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/lords")
@AllArgsConstructor
@Slf4j
public class LordController {

    private final LordService lordService;

    @PostMapping
    public ResponseEntity<?> createLord(@Valid @ModelAttribute LordModel lordModel) {
        lordService.createLord(lordModel);
        log.info("{}", lordModel);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{lordId}")
    @SneakyThrows
    public ResponseEntity<?> updateLord(@PathVariable("lordId") Long lordId, @Valid @ModelAttribute LordModel lordModel) {
        return ResponseEntity.ok(lordService.updateLord(lordId, lordModel));
    }

    @GetMapping("/{lordId}")
    public ResponseEntity<?> getLordById(@PathVariable("lordId") Long lordId) {
        return ResponseEntity.ok(lordService.findLordById(lordId));
    }

    @GetMapping
    public ResponseEntity<List<Lord>> findFirst10OrderByAgeDesc() {
        return ResponseEntity.ok(lordService.get10YoungestLords());
    }

    @GetMapping("/slackers")
    public ResponseEntity<List<Lord>> findAllSlackerLords() {
        return ResponseEntity.ok(lordService.findAllSlackerLords());
    }
}
