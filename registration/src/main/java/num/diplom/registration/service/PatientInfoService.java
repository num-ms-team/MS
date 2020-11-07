package num.diplom.registration.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.registration.model.PatientId;
import num.diplom.registration.model.PatientInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientInfoService {
    void save(PatientInfo patientInfo);

    List<PatientInfo> findAll();

    List<PatientInfo> findAllById(List<PatientId> patients);

    PatientInfo findById(PatientId patientId) throws RepositoryException;

    void delete(PatientId patientId);
}
