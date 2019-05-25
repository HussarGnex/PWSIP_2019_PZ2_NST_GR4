package pl.edu.pwsip.Reception.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsip.Reception.exceptions.PersonNotFoundException;
import pl.edu.pwsip.Reception.exceptions.VisitNotFoundException;
import pl.edu.pwsip.Reception.models.Person;
import pl.edu.pwsip.Reception.models.Visit;
import pl.edu.pwsip.Reception.repositories.PersonRepository;
import pl.edu.pwsip.Reception.repositories.VisitRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler wizyty, odpowiada za obsługę zapytań HTTP
 */
@RestController
public class VisitController {

    @Autowired private VisitRepository visitRepository;
    @Autowired private PersonRepository personRepository;

    @GetMapping("/visits/{idVisit}")
    public Visit getVisit(@PathVariable String idVisit) {
        return visitRepository.findById(idVisit).orElseThrow(() -> new VisitNotFoundException(idVisit));
    }

    @PostMapping("/visits")
    public Visit addVisit(@RequestBody Visit visit) {
        Visit result = visitRepository.save(visit);

        Person doctor = personRepository.findById(result.getDoctorId())
                .orElseThrow(() -> new PersonNotFoundException(result.getDoctorId()));
        doctor.getVisits().add(result.getId());
        personRepository.save(doctor);

        Person patient = personRepository.findById(result.getPatientId())
                .orElseThrow(() -> new PersonNotFoundException(result.getPatientId()));
        patient.getVisits().add(result.getId());
        personRepository.save(patient);

        return result;
    }

    @PutMapping("/visits/{idVisit}")
    public Visit updateVisit(@RequestBody Visit visit, @PathVariable String idVisit) {
        visit.setId(idVisit);
        return visitRepository.save(visit);
    }

    @DeleteMapping("/visits/{idVisit}")
    public void deleteVisit(@PathVariable String idVisit) {
        visitRepository.delete(getVisit(idVisit));
    }

    @GetMapping("/visits")
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @GetMapping("/visits/search")
    public List<Visit> getAllVisitsBy(@RequestParam String by, @RequestParam String what) {
        if(by.equals("doctor"))
            return visitRepository.findByDoctorId(what).orElseGet(() -> new ArrayList<>());
        if(by.equals("patient"))
            return visitRepository.findByPatientId(what).orElseGet(() -> new ArrayList<>());
        if(by.equals("date"))
            return visitRepository.findByVisitDate(LocalDate.parse(what)).orElseGet(() -> new ArrayList<>());
        return new ArrayList<>();
    }

}
