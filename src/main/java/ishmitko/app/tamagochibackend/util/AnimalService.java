package ishmitko.app.tamagochibackend.util;

import ishmitko.app.tamagochibackend.dto.AnimalDTO;
import ishmitko.app.tamagochibackend.dto.AnimalNameHolder;
import ishmitko.app.tamagochibackend.exceptions.AnimalNotFoundException;
import ishmitko.app.tamagochibackend.model.Animal;
import ishmitko.app.tamagochibackend.repositories.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import static ishmitko.app.tamagochibackend.util.ErrorMessages.ANIMAL_NOT_FOUND;

@Service
public class AnimalService {

    @Value("${max_health}")
    private double maxHealth;

    @Value("${max_satiation}")
    private double maxSatiation;

    private double speedK = 60d;//50000d / 3600000d;

    private final AnimalRepository animalRepository;


    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    public AnimalDTO getAnimal(String uuid) {
        Animal animal = animalRepository.findByUuid(uuid)
                .orElseThrow(()-> new AnimalNotFoundException(ANIMAL_NOT_FOUND));

//        Same as 39-40 lines
//
//        Optional<Animal> optionalAnimalReturnedByRepository = animalRepository.findByUuid(uuid);
//
//        Animal extractedAnimalFromOptional = optionalAnimalReturnedByRepository
//                .orElseThrow(()-> new AnimalNotFoundException(ANIMAL_NOT_FOUND));

        return mapAnimalToDTO(animal);
    }

    public Animal findById(int id) {
        return animalRepository.findById(id).orElse(null);
    }

    public AnimalDTO createAnimal(AnimalNameHolder animalNameHolder) {
        Animal animal = convertToAnimal(animalNameHolder);
        save(animal);
        return mapAnimalToDTO(animal);
    }

    private Animal convertToAnimal(AnimalNameHolder animalNameHolder) {
        Animal animal = new Animal();
        animal.setName(animalNameHolder.getName());
        animal.setUuid(UUID.randomUUID().toString());
        animal.setAge(1);
        animal.setHealth(maxHealth);
        animal.setSatiation(maxSatiation);
        return animal;
    }

    private AnimalDTO mapAnimalToDTO(Animal animal) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(animal, AnimalDTO.class);
    }
}

