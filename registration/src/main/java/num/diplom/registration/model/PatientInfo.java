package num.diplom.registration.model;

import num.diplom.base.model.Entity;

import java.time.LocalDate;

public class PatientInfo implements Entity<PatientInfo> {
    private final PatientId patientId;
    private String firstName;
    private String lastName;
    private PatientGender gender;
    private LocalDate dateOfBirth;
    private String education;
    private String profession;

    public PatientInfo(PatientId patientId) {
        this.patientId = patientId;
    }

    public PatientInfo(
            PatientId patientId,
            String firstName,
            String lastName,
            PatientGender gender,
            LocalDate dateOfBirth,
            String education,
            String profession
    ) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.education = education;
        this.profession = profession;
    }

    public PatientId getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PatientGender getGender() {
        return gender;
    }

    public void setGender(PatientGender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean sameEntityAs(PatientInfo patientInfo) {
        return patientInfo.patientId.equals(patientId);
    }
}
