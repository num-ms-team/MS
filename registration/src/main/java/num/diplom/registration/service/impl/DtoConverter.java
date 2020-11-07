package num.diplom.registration.service.impl;

import num.diplom.registration.model.*;
import num.diplom.registration.repository.model.MongoPatient;
import num.diplom.registration.repository.model.MongoPatientContact;
import num.diplom.registration.repository.model.MongoPatientInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public final class DtoConverter {

    private DtoConverter() {
    }

    public static Patient toPatient(MongoPatient patient) {
        return new Patient(
                PatientId.valueOf(patient.getId()),
                patient.getRegisterNumber(),
                patient.getCreatedDate(),
                patient.getSisiId()
        );
    }

    public static List<Patient> toPatients(List<MongoPatient> mongoPatients) {
        return mongoPatients.stream().map(DtoConverter::toPatient).collect(Collectors.toList());
    }

    public static List<Patient> toPatients(Page<MongoPatient> mongoPatients) {
        return mongoPatients.stream().map(DtoConverter::toPatient).collect(Collectors.toList());
    }

    public static PatientInfo toPatientInfo(MongoPatientInfo mongoPatientInfo) {
        return new PatientInfo(
                PatientId.valueOf(mongoPatientInfo.getId()),
                mongoPatientInfo.getFirstName(),
                mongoPatientInfo.getLastName(),
                PatientGender.valueOf(mongoPatientInfo.getGender()),
                mongoPatientInfo.getDateOfBirth(),
                mongoPatientInfo.getEducation(),
                mongoPatientInfo.getProfession()
        );
    }

    public static MongoPatientInfo toMongoPatientInfo(PatientInfo patientInfo) {
        return new MongoPatientInfo(
                patientInfo.getPatientId().getId(),
                patientInfo.getFirstName(),
                patientInfo.getLastName(),
                patientInfo.getGender().name(),
                patientInfo.getDateOfBirth(),
                patientInfo.getEducation(),
                patientInfo.getProfession()
        );
    }

    public static PatientContact toPatientContact(MongoPatientContact mongoPatientContact) {
        return new PatientContact(
                PatientId.valueOf(mongoPatientContact.getId()),
                mongoPatientContact.getPhoneNumber(),
                mongoPatientContact.getEmail(),
                mongoPatientContact.getHomeAddress(),
                mongoPatientContact.getWorkAddress()
        );
    }

    public static MongoPatientContact toMongoPatientContact(PatientContact patientContact) {
        return new MongoPatientContact(
                patientContact.getPatientId().getId(),
                patientContact.getPhoneNumber(),
                patientContact.getEmail(),
                patientContact.getHomeAddress(),
                patientContact.getWorkAddress()
        );
    }

}
