package num.diplom.registration.usecase;

import num.diplom.base.usecase.UseCase;
import num.diplom.registration.model.Patient;
import num.diplom.registration.model.PatientContact;
import num.diplom.registration.model.PatientInfo;
import num.diplom.registration.service.PatientContactService;
import num.diplom.registration.service.PatientInfoService;
import num.diplom.registration.service.PatientService;
import num.diplom.registration.usecase.model.PatientDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class PatientUseCase<I, O> implements UseCase<I, O> {
    protected final PatientService patientService;
    protected final PatientInfoService patientInfoService;
    protected final PatientContactService patientContactService;

    public PatientUseCase(
            PatientService patientService,
            PatientInfoService patientInfoService,
            PatientContactService patientContactService
    ) {
        this.patientService = patientService;
        this.patientInfoService = patientInfoService;
        this.patientContactService = patientContactService;
    }

    protected List<PatientDto> toPatientDtos(
            List<Patient> patients,
            Map<String, PatientInfo> infos,
            Map<String, PatientContact> contacts
    ) {
        return patients.stream().map(patient -> new PatientDto.Builder(
                patient.getPatientId().getId(),
                patient.getRegisterNumber(),
                patient.getCreatedDate(),
                patient.getSisiId()
        )).peek(builder -> {
            PatientInfo info = infos.get(builder.getId());
            builder.withFirstName(info != null ? info.getFirstName() : null)
                    .withLastName(info != null ? info.getLastName() : null)
                    .withGender(info != null ?
                            info.getGender() != null ?
                                    info.getGender().name() : null : null)
                    .withDateOfBirth(info != null ? info.getDateOfBirth() : null)
                    .withEducation(info != null ? info.getEducation() : null)
                    .withProfession(info != null ? info.getProfession() : null);
        }).peek(builder -> {
            PatientContact contact = contacts.get(builder.getId());
            builder.withPhoneNumber(contact != null ? contact.getPhoneNumber() : null)
                    .withEmail(contact != null ? contact.getEmail() : null)
                    .withHomeAddress(contact != null ? contact.getHomeAddress() : null)
                    .withWorkAddress(contact != null ? contact.getWorkAddress() : null);
        }).map(PatientDto.Builder::build).collect(Collectors.toList());
    }

    protected List<PatientDto> toPatientDtos(
            List<Patient> patients,
            Iterable<PatientInfo> infos,
            Iterable<PatientContact> contacts
    ) {
        Map<String, PatientDto.Builder> builders = patients.stream().map(patient -> new PatientDto.Builder(
                patient.getPatientId().getId(),
                patient.getRegisterNumber(),
                patient.getCreatedDate(),
                patient.getSisiId()
        )).collect(Collectors.toMap(PatientDto.Builder::getId, builder -> builder));

        infos.iterator().forEachRemaining(info ->
                builders.get(info.getPatientId().getId())
                        .withFirstName(info.getFirstName())
                        .withLastName(info.getLastName())
                        .withGender(info.getGender() != null ? info.getGender().name() : null)
                        .withDateOfBirth(info.getDateOfBirth())
                        .withEducation(info.getEducation())
                        .withProfession(info.getProfession())
        );

        contacts.iterator().forEachRemaining(contact ->
                builders.get(contact.getPatientId().getId())
                        .withPhoneNumber(contact.getPhoneNumber())
                        .withEmail(contact.getEmail())
                        .withHomeAddress(contact.getHomeAddress())
                        .withWorkAddress(contact.getWorkAddress())
        );

        return builders.values().stream().map(PatientDto.Builder::build).collect(Collectors.toList());
    }

    protected PatientDto toPatientDto(Patient patient, PatientInfo info, PatientContact contact) {
        return new PatientDto.Builder(
                patient.getPatientId().getId(),
                patient.getRegisterNumber(),
                patient.getCreatedDate(),
                patient.getSisiId()
        )
                .withFirstName(info != null ? info.getFirstName() : null)
                .withLastName(info != null ? info.getLastName() : null)
                .withGender(info != null ?
                        info.getGender() != null ?
                                info.getGender().name() : null : null)
                .withDateOfBirth(info != null ? info.getDateOfBirth() : null)
                .withEducation(info != null ? info.getEducation() : null)
                .withProfession(info != null ? info.getProfession() : null)
                .withPhoneNumber(contact != null ? contact.getPhoneNumber() : null)
                .withEmail(contact != null ? contact.getEmail() : null)
                .withHomeAddress(contact != null ? contact.getHomeAddress() : null)
                .withWorkAddress(contact != null ? contact.getWorkAddress() : null)
                .build();
    }
}
