package pl.edu.pwsip.Reception.exceptions;

/**
 * Wyjątek oznaczający, że osoba o podanym identyfikatorze nie została odnaleziona
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String id) {
        super("Person with id = " + id + " not found!");
    }
}
