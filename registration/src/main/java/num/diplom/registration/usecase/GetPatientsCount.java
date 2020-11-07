package num.diplom.registration.usecase;

import num.diplom.registration.service.PatientContactService;
import num.diplom.registration.service.PatientInfoService;
import num.diplom.registration.service.PatientService;

public class GetPatientsCount extends PatientUseCase<Void, Integer> {
    public GetPatientsCount(
            PatientService patientService,
            PatientInfoService patientInfoService,
            PatientContactService patientContactService
    ) {
        super(patientService, patientInfoService, patientContactService);
    }

    @Override
    public Integer execute(Void input) {
        return patientService.count();
    }
}
