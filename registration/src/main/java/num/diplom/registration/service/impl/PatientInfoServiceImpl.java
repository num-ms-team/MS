package num.diplom.registration.service.impl;

import num.diplom.base.exception.RepositoryException;
import num.diplom.registration.model.PatientId;
import num.diplom.registration.model.PatientInfo;
import num.diplom.registration.repository.PatientInfoRepository;
import num.diplom.registration.repository.model.MongoPatientInfo;
import num.diplom.registration.service.PatientInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static num.diplom.base.constant.MongoConstants.PATIENT_INFO_NOT_FOUND;
import static num.diplom.registration.service.impl.DtoConverter.toMongoPatientInfo;
import static num.diplom.registration.service.impl.DtoConverter.toPatientInfo;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    private final PatientInfoRepository patientInfoRepository;

    public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }

    @Override
    public void save(PatientInfo patientInfo) {
        patientInfoRepository.save(toMongoPatientInfo(patientInfo));
    }

    @Override
    public List<PatientInfo> findAll() {
        return patientInfoRepository.findAll().stream()
                .map(DtoConverter::toPatientInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientInfo> findAllById(List<PatientId> patients) {
        List<PatientInfo> infos = new ArrayList<>();

        patientInfoRepository.findAllById(
                patients.stream()
                        .map(PatientId::getId)
                        .collect(Collectors.toList())
        ).iterator().forEachRemaining(info -> infos.add(toPatientInfo(info)));

        return infos;
    }

    @Override
    public PatientInfo findById(PatientId patientId) throws RepositoryException {
        Optional<MongoPatientInfo> optionalUserInfo = patientInfoRepository.findById(patientId.getId());
        return toPatientInfo(optionalUserInfo.orElseThrow(() -> new RepositoryException(PATIENT_INFO_NOT_FOUND)));
    }

    @Override
    public void delete(PatientId patientId) {
        patientInfoRepository.deleteById(patientId.getId());
    }
}
