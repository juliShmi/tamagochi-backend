package ishmitko.app.tamagochibackend.controllers;

import ishmitko.app.tamagochibackend.dto.AnimalDTO;
import ishmitko.app.tamagochibackend.dto.AnimalNameHolder;
import ishmitko.app.tamagochibackend.exceptions.AnimalNotFoundException;
import ishmitko.app.tamagochibackend.util.AnimalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/tamagochi")
public class TamagochiController {


    private final AnimalService animalService;

    public TamagochiController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(value="/{id}", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"})
    public ResponseEntity<?> getAnimal(@PathVariable("id") String uuid) {
        AnimalDTO animalDTO = animalService.getAnimal(uuid);
        return ResponseEntity.ok()
                .body(animalDTO);
    }

    @PostMapping(value = "/animal", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"})
    public ResponseEntity<?> createAnimal(@RequestBody AnimalNameHolder animalNameHolder) {
        AnimalDTO animalDTO = animalService.createAnimal(animalNameHolder);
        return ResponseEntity.ok()
                .body(animalDTO);
    }
}
