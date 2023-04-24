package ishmitko.app.tamagochibackend.controllers;

import ishmitko.app.tamagochibackend.dto.AnimalDTO;
import ishmitko.app.tamagochibackend.dto.AnimalNameHolder;
import ishmitko.app.tamagochibackend.model.Animal;
import ishmitko.app.tamagochibackend.util.AnimalService;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tamagochi")
public class TamagochiController {


    private final AnimalService animalService;


    public TamagochiController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Animal getAnimal(@PathVariable("id") int id) {
        return animalService.findById(id);
    }


    @CrossOrigin(origins = "http://localhost:3002")
    @PostMapping(value = "/animal", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"})
    public ResponseEntity<?> createAnimal(@RequestBody AnimalNameHolder animalNameHolder) {
        System.out.println(animalNameHolder);
        AnimalDTO animalDTO = animalService.createAnimal(animalNameHolder);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        System.out.println(responseHeaders);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(animalDTO);
    }
}
