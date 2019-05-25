package pl.edu.pwsip.Reception.exceptions;

/**
 * Wyjątek oznaczający, że wizyta o podanym identyfikatorze nie istnieje
 */
public class VisitNotFoundException extends RuntimeException {
    public VisitNotFoundException(String idVisit) {
        super("Visit with id = " + idVisit + " not found!");
    }
}
