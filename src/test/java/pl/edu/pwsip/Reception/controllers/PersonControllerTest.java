package pl.edu.pwsip.Reception.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pwsip.Reception.exceptions.PersonNotFoundException;
import pl.edu.pwsip.Reception.models.Person;
import pl.edu.pwsip.Reception.repositories.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PersonControllerTest {

    @Mock private PersonRepository personRepository;
    @InjectMocks private PersonController personController;
    private List<Person> persons;

    @Before
    public void setUpClass() {
        this.persons = new ArrayList<>();
        this.persons.add(new Person("zxcv", "Michał", "Kowalski", "patient",
                LocalDate.parse("1994-01-03"), new ArrayList<>(Arrays.asList("visit_id_1")), new ArrayList<>()));
        this.persons.add(new Person("zxcv1", "Karol", "Kowalski", "doctor",
                LocalDate.parse("1994-03-01"), new ArrayList<>(Arrays.asList("visit_id_1")),
                new ArrayList<>(Arrays.asList("zxcv"))));
    }

    @Test
    public void findByNameTest() {
        List<Person> personsByName = this.persons.stream()
                .filter( p -> p.getName().equals("Michał")).collect(Collectors.toList());
        when(personRepository.findByName("Michał")).thenReturn(Optional.of(personsByName));
        assertEquals(1, personController.getAllPersonsBy("name", "Michał").size());
    }

    @Test
    public void findBySurnameTest() {
        List<Person> personsBySurname = this.persons.stream()
                .filter( p -> p.getSurname().equals("Kowalski")).collect(Collectors.toList());
        when(personRepository.findBySurname("Kowalski")).thenReturn(Optional.of(personsBySurname));
        assertEquals(2, personController.getAllPersonsBy("surname", "Kowalski").size());
    }

    @Test
    public void findByRoleTest() {
        List<Person> personsByRole = this.persons.stream()
                .filter( p -> p.getRole().equals("doctor")).collect(Collectors.toList());
        when(personRepository.findByRole("doctor")).thenReturn(Optional.of(personsByRole));
        assertEquals(1, personController.getAllPersonsBy("role", "doctor").size());
    }

    @Test
    public void findByBirthDateTest() {
        List<Person> personsByBirthDate = this.persons.stream()
                .filter( p -> p.getBirthDate().equals(LocalDate.parse("1994-03-01"))).collect(Collectors.toList());
        when(personRepository.findByBirthDate(LocalDate.parse("1994-03-01"))).thenReturn(Optional.of(personsByBirthDate));
        assertEquals(1, personController.getAllPersonsBy("birthdate", "1994-03-01").size());
    }

    @Test
    public void findByVisitsTest() {
        List<Person> personsByVisits = this.persons.stream()
                .filter( p -> p.getVisits().contains("visit_id_1")).collect(Collectors.toList());
        when(personRepository.findByVisits("visit_id_1")).thenReturn(Optional.of(personsByVisits));
        assertEquals(2, personController.getAllPersonsBy("visit", "visit_id_1").size());
    }

    @Test
    public void findByPatientsTest() {
        List<Person> personsByPatients = this.persons.stream()
                .filter( p -> p.getPatients().contains("zxcv")).collect(Collectors.toList());
        when(personRepository.findByPatients("zxcv")).thenReturn(Optional.of(personsByPatients));
        assertEquals(1, personController.getAllPersonsBy("patient", "zxcv").size());
    }

    @Test(expected = PersonNotFoundException.class)
    public void checkFindByIdIfThrowsPersonNotFoundException() {
        when(personRepository.findById("")).thenReturn(Optional.ofNullable(null));
        personController.getPerson("");
    }
}
