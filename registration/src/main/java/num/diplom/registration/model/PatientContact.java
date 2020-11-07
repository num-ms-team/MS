package num.diplom.registration.model;

import num.diplom.base.model.Entity;

public class PatientContact implements Entity<PatientContact> {
    private final PatientId patientId;
    private String phoneNumber;
    private String email;
    private String homeAddress;
    private String workAddress;

    public PatientContact(
            PatientId patientId,
            String phoneNumber,
            String email,
            String homeAddress,
            String workAddress
    ) {
        this.patientId = patientId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public PatientId getPatientId() {
        return patientId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    @Override
    public boolean sameEntityAs(PatientContact patientContact) {
        return patientContact.patientId.equals(patientId);
    }
}
