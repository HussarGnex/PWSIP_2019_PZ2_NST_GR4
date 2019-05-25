package pl.edu.pwsip.Reception.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pwsip.Reception.models.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repozytorium osoby, odpowiada za komunikację aplikacji z bazą danych MongoDB
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    Optional<List<Person>> findByName(String name);
    Optional<List<Person>> findBySurname(String surname);
    Optional<List<Person>> findByBirthDate(LocalDate birthDate);
    Optional<List<Person>> findByRole(String role);
    Optional<List<Person>> findByPatients(String id);
    Optional<List<Person>> findByVisits(String id);

}
