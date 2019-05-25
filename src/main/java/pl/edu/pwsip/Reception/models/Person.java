package pl.edu.pwsip.Reception.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model osoby
 */
public class Person {

    @Id private String id;
    private String name;
    private String surname;
    private String role;
    private LocalDate birthDate;
    private List<String> visits;
    private List<String> patients;

    public Person(String id, String name, String surname, String role, LocalDate birthDate, List<String> visits, List<String> patients) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.birthDate = birthDate;
        this.visits = visits;
        this.patients = patients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getVisits() {
        if(visits == null)
            visits = new ArrayList<>();
        return visits;
    }

    public void setVisits(List<String> visits) {
        this.visits = visits;
    }

    public List<String> getPatients() {
        if(patients == null)
            patients = new ArrayList<>();
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
