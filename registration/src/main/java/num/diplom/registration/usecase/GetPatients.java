package num.diplom.registration.usecase;

import num.diplom.base.usecase.filter.Filter;
import num.diplom.registration.model.Patient;
import num.diplom.registration.model.PatientContact;
import num.diplom.registration.model.PatientId;
import num.diplom.registration.model.PatientInfo;
import num.diplom.registration.service.PatientContactService;
import num.diplom.registration.service.PatientInfoService;
import num.diplom.registration.service.PatientService;
import num.diplom.registration.usecase.model.PatientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetPatients extends PatientUseCase<Filter, List<PatientDto>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetPatients.class);

    public GetPatients(
            PatientService patientService,
            PatientInfoService patientInfoService,
            PatientContactService patientContactService
    ) {
        super(patientService, patientInfoService, patientContactService);
    }

    @Override
    public List<PatientDto> execute(Filter input) {

        List<Patient> patients;

        if (input == null || (input.getDateFilter() == null && input.getTableFilter() == null)) {
            patients = patientService.findAll();
        } else if (input.getDateFilter() == null && input.getTableFilter() != null) {
            patients = patientService.findAll(input.getTableFilter().getPage(), input.getTableFilter().getSize());
        } else if (input.getDateFilter() != null && input.getTableFilter() == null) {
            patients = patientService.findAll(input.getDateFilter().getStartDate(), input.getDateFilter().getEndDate());
        } else {
            patients = patientService.findAll(
                    input.getTableFilter().getPage(),
                    input.getTableFilter().getSize(),
                    input.getDateFilter().getStartDate(),
                    input.getDateFilter().getEndDate()
            );
        }

        List<PatientId> patientIds = patients.stream().map(Patient::getPatientId).collect(Collectors.toList());

        Map<String, PatientInfo> patientInfos = patientInfoService.findAllById(patientIds).stream()
                .collect(Collectors.toMap(
                        patientInfo -> patientInfo.getPatientId().getId(),
                        patientInfo -> patientInfo
                ));

        Map<String, PatientContact> patientContacts = patientContactService.findAllById(patientIds).stream()
                .collect(Collectors.toMap(
                        patientContact -> patientContact.getPatientId().getId(),
                        patientContact -> patientContact
                ));

        return toPatientDtos(patients, patientInfos, patientContacts);
    }
}
