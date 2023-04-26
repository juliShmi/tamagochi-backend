package ishmitko.app.tamagochibackend.repositories;

import ishmitko.app.tamagochibackend.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
