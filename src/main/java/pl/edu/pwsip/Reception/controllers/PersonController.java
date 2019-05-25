package pl.edu.pwsip.Reception.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsip.Reception.exceptions.PersonNotFoundException;
import pl.edu.pwsip.Reception.models.Person;
import pl.edu.pwsip.Reception.repositories.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler osoby, odpowiada za obsługę zapytań HTTP
 */
@RestController
public class PersonController {

    @Autowired private PersonRepository personRepository;

    @GetMapping("/persons/{idPerson}")
    public Person getPerson(@PathVariable String idPerson) {
        return personRepository.findById(idPerson).orElseThrow(() -> new PersonNotFoundException(idPerson));
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping("/persons/{idPerson}")
    public void deletePerson(@PathVariable String idPerson) {
        personRepository.delete(getPerson(idPerson));
    }

    @PutMapping("/persons/{idPerson}")
    public Person updatePerson(@RequestBody Person person, @PathVariable String idPerson) {
        person.setId(idPerson);
        return personRepository.save(person);
    }

    @GetMapping("/persons/all")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/search")
    public List<Person> getAllPersonsBy(@RequestParam String by, @RequestParam String what) {
        if(by.equals("name"))
            return personRepository.findByName(what).orElseGet(() -> new ArrayList<>());
        if(by.equals("surname"))
            return personRepository.findBySurname(what).orElseGet(() -> new ArrayList<>());
        if(by.equals("birthdate"))
            return personRepository.findByBirthDate(LocalDate.parse(what)).orElseGet(() -> new ArrayList<>());
        if(by.equals("role"))
            return personRepository.findByRole(what).orElseGet(() -> new ArrayList<>());
        if(by.equals("patient"))
            return personRepository.findByPatients(what).orElseGet(() -> new ArrayList<>());
        if(by.equals("visit"))
            return personRepository.findByVisits(what).orElseGet(() -> new ArrayList<>());
        return new ArrayList<>();
    }

}
