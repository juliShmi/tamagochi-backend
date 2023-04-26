package ishmitko.app.tamagochibackend.repositories;

import ishmitko.app.tamagochibackend.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Optional<Animal> findByUuid(String uuid);
}
