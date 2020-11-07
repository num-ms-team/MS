package num.diplom.registration.service.impl;

import num.diplom.base.exception.RepositoryException;
import num.diplom.registration.model.PatientContact;
import num.diplom.registration.model.PatientId;
import num.diplom.registration.repository.PatientContactRepository;
import num.diplom.registration.repository.model.MongoPatientContact;
import num.diplom.registration.service.PatientContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static num.diplom.base.constant.MongoConstants.PATIENT_CONTACT_NOT_FOUND;
import static num.diplom.registration.service.impl.DtoConverter.toPatientContact;

@Service
public class PatientContactServiceImpl implements PatientContactService {

    private final PatientContactRepository patientContactRepository;

    public PatientContactServiceImpl(PatientContactRepository patientContactRepository) {
        this.patientContactRepository = patientContactRepository;
    }

    @Override
    public void save(PatientContact patientContact) {
        patientContactRepository.save(DtoConverter.toMongoPatientContact(patientContact));
    }

    @Override
    public List<PatientContact> findAll() {
        return patientContactRepository.findAll().stream()
                .map(DtoConverter::toPatientContact)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientContact> findAllById(List<PatientId> patients) {
        List<PatientContact> contacts = new ArrayList<>();

        patientContactRepository.findAllById(
                patients.stream()
                        .map(PatientId::getId)
                        .collect(Collectors.toList())
        ).iterator().forEachRemaining(contact -> contacts.add(toPatientContact(contact)));

        return contacts;
    }

    @Override
    public PatientContact findById(PatientId patientId) throws RepositoryException {
        Optional<MongoPatientContact> optionalUserContact = patientContactRepository.findById(patientId.getId());
        return toPatientContact(optionalUserContact.orElseThrow(() -> new RepositoryException(PATIENT_CONTACT_NOT_FOUND)));
    }

    @Override
    public void delete(PatientId patientId) {
        patientContactRepository.deleteById(patientId.getId());
    }
}
