package num.diplom.registration.model;

import num.diplom.base.model.Entity;

import java.time.LocalDate;

public class Patient implements Entity<Patient> {
    private final PatientId patientId;
    private final String registerNumber;
    private final LocalDate createdDate;
    private String sisiId;

    public Patient(PatientId patientId, String registerNumber, LocalDate createdDate) {
        this.patientId = patientId;
        this.registerNumber = registerNumber;
        this.createdDate = createdDate;
    }

    public Patient(PatientId patientId, String registerNumber, LocalDate createdDate, String sisiId) {
        this.patientId = patientId;
        this.registerNumber = registerNumber;
        this.createdDate = createdDate;
        this.sisiId = sisiId;
    }

    public PatientId getPatientId() {
        return patientId;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getSisiId() {
        return sisiId;
    }

    public void setSisiId(String sisiId) {
        this.sisiId = sisiId;
    }

    @Override
    public boolean sameEntityAs(Patient patient) {
        return patient.patientId.equals(patientId);
    }
}
