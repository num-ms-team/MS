package num.diplom.registration.usecase;

import num.diplom.base.exception.UseCaseException;
import num.diplom.registration.model.*;
import num.diplom.registration.service.PatientContactService;
import num.diplom.registration.service.PatientInfoService;
import num.diplom.registration.service.PatientService;
import num.diplom.registration.usecase.model.PatientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.Validate;

import java.time.LocalDate;
import java.util.UUID;

import static num.diplom.base.constant.RestConstants.INVALID_INPUT;
import static num.diplom.base.constant.UseCaseConstants.INVALID_REGISTER_NUMBER;
import static num.diplom.base.constant.UseCaseConstants.REGISTER_NUMBER_REGEX;

public class CreatePatient extends PatientUseCase<PatientDto, PatientId> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePatient.class);

    public CreatePatient(
            PatientService patientService,
            PatientInfoService patientInfoService,
            PatientContactService patientContactService
    ) {
        super(patientService, patientInfoService, patientContactService);
    }

    @Override
    public PatientId execute(PatientDto input) throws UseCaseException {

        try {
            Validate.notNull(input, INVALID_INPUT);
            Validate.notEmpty(input.getRegisterNumber(), INVALID_REGISTER_NUMBER);
        } catch (IllegalArgumentException e) {
            throw new UseCaseException(e.getMessage(), e);
        }

        if (!input.getRegisterNumber().matches(REGISTER_NUMBER_REGEX)) {
            throw new UseCaseException(INVALID_REGISTER_NUMBER);
        }

        PatientId patientId = patientService.save(new Patient(
                PatientId.valueOf(UUID.randomUUID().toString()),
                input.getRegisterNumber(),
                LocalDate.now(),
                input.getSisiId()
        ));

        patientInfoService.save(new PatientInfo(
                patientId,
                input.getFirstName(),
                input.getLastName(),
                PatientGender.valueOf(input.getGender()),
                input.getDateOfBirth(),
                input.getEducation(),
                input.getProfession()
        ));

        patientContactService.save(new PatientContact(
                patientId,
                input.getPhoneNumber(),
                input.getEmail(),
                input.getHomeAddress(),
                input.getWorkAddress()
        ));

        return patientId;
    }
}
