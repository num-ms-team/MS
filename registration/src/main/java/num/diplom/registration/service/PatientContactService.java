package num.diplom.registration.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.registration.model.PatientContact;
import num.diplom.registration.model.PatientId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientContactService {
    void save(PatientContact patientContact);

    List<PatientContact> findAll();

    List<PatientContact> findAllById(List<PatientId> patients);

    PatientContact findById(PatientId patientId) throws RepositoryException;

    void delete(PatientId patientId);
}
