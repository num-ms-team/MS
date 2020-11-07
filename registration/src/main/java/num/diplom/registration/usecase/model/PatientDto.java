package num.diplom.registration.usecase.model;

import java.time.LocalDate;

public class PatientDto {
    private String id;

    // Patient
    private String registerNumber;
    private LocalDate createdDate;
    private String sisiId;

    // Info
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String education;
    private String profession;

    // Contact
    private String phoneNumber;
    private String email;
    private String homeAddress;
    private String workAddress;

    public String getId() {
        return id;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public String getSisiId() {
        return sisiId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public String getProfession() {
        return profession;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public static class Builder {
        private final String id;

        // Patient
        private final String registerNumber;
        private final LocalDate createdDate;
        private String sisiId;

        // Info
        private String firstName;
        private String lastName;
        private String gender;
        private LocalDate dateOfBirth;
        private String education;
        private String profession;

        // Contact
        private String phoneNumber;
        private String email;
        private String homeAddress;
        private String workAddress;

        public String getId() {
            return id;
        }

        public Builder(String id, String registerNumber, LocalDate createdDate) {
            this.id = id;
            this.registerNumber = registerNumber;
            this.createdDate = createdDate;
        }

        public Builder(String id, String registerNumber, LocalDate createdDate, String sisiId) {
            this.id = id;
            this.registerNumber = registerNumber;
            this.createdDate = createdDate;
            this.sisiId = sisiId;
        }

        public Builder withSisiId(String sisiId) {
            this.sisiId = sisiId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withEducation(String education) {
            this.education = education;
            return this;
        }

        public Builder withProfession(String profession) {
            this.profession = profession;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder withWorkAddress(String workAddress) {
            this.workAddress = workAddress;
            return this;
        }

        public PatientDto build() {
            PatientDto output = new PatientDto();

            output.id = id;
            output.registerNumber = registerNumber;
            output.createdDate = createdDate;
            output.firstName = firstName;
            output.lastName = lastName;
            output.gender = gender;
            output.dateOfBirth = dateOfBirth;
            output.phoneNumber = phoneNumber;
            output.email = email;
            output.sisiId = sisiId;
            output.education = education;
            output.profession = profession;
            output.homeAddress = homeAddress;
            output.workAddress = workAddress;

            return output;
        }
    }
}
