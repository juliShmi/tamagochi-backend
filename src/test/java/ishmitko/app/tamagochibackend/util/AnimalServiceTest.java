package ishmitko.app.tamagochibackend.util;

import ishmitko.app.tamagochibackend.dto.AnimalDTO;
import ishmitko.app.tamagochibackend.exceptions.AnimalNotFoundException;
import ishmitko.app.tamagochibackend.model.Animal;
import ishmitko.app.tamagochibackend.repositories.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

//    @InjectMocks
//    private AnimalService animalService;
//
//    @Mock
//    private AnimalRepository animalRepository;
//
//
//    @Test
//    void test() {
//        String uuid = "test-uuid";
//        when(animalRepository.findByUuid(uuid)).thenReturn(Optional.empty());
//        assertThrows(AnimalNotFoundException.class, () -> animalService.getAnimal(uuid));
//    }
}
