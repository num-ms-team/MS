package num.diplom.registration.usecase;

import num.diplom.registration.model.Patient;
import num.diplom.registration.service.PatientContactService;
import num.diplom.registration.service.PatientInfoService;
import num.diplom.registration.service.PatientService;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllRegisters extends PatientUseCase<Void, List<String>> {

    public GetAllRegisters(PatientService patientService, PatientInfoService patientInfoService, PatientContactService patientContactService) {
        super(patientService, patientInfoService, patientContactService);
    }

    @Override
    public List<String> execute(Void input) {
        return patientService.findAll().stream().map(Patient::getRegisterNumber).collect(Collectors.toList());
    }
}
