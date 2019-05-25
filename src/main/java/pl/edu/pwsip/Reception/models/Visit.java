package pl.edu.pwsip.Reception.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Model wizyty
 */
public class Visit {

    @Id private String id;
    private String doctorId;
    private String patientId;
    private Date visitDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
