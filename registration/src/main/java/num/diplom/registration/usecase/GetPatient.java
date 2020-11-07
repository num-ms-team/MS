package num.diplom.registration.usecase;

import num.diplom.base.exception.RepositoryException;
import num.diplom.base.exception.UseCaseException;
import num.diplom.registration.model.Patient;
import num.diplom.registration.model.PatientContact;
import num.diplom.registration.model.PatientId;
import num.diplom.registration.model.PatientInfo;
import num.diplom.registration.service.PatientContactService;
import num.diplom.registration.service.PatientInfoService;
import num.diplom.registration.service.PatientService;
import num.diplom.registration.usecase.model.PatientDto;
import org.thymeleaf.util.Validate;

import static num.diplom.base.constant.UseCaseConstants.INPUT_CANNOT_BE_NULL_OR_EMPTY;

public class GetPatient extends PatientUseCase<String, PatientDto> {

    public GetPatient(
            PatientService patientService,
            PatientInfoService patientInfoService,
            PatientContactService patientContactService
    ) {
        super(patientService, patientInfoService, patientContactService);
    }

    @Override
    public PatientDto execute(String input) throws UseCaseException {
        try {
            Validate.notEmpty(input, INPUT_CANNOT_BE_NULL_OR_EMPTY);
        } catch (IllegalArgumentException e) {
            throw new UseCaseException(e.getMessage(), e);
        }

        try {
            Patient patient = patientService.findById(PatientId.valueOf(input));
            PatientInfo info = patientInfoService.findById(patient.getPatientId());
            PatientContact contact = patientContactService.findById(patient.getPatientId());
            return toPatientDto(patient, info, contact);
        } catch (RepositoryException e) {
            throw new UseCaseException(e.getMessage(), e);
        }
    }
}
