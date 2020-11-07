package num.diplom.registration.service.impl;

import num.diplom.base.exception.RepositoryException;
import num.diplom.registration.model.Patient;
import num.diplom.registration.model.PatientId;
import num.diplom.registration.repository.PatientRepository;
import num.diplom.registration.repository.model.MongoPatient;
import num.diplom.registration.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static num.diplom.base.constant.MongoConstants.PATIENT_NOT_FOUND;
import static num.diplom.registration.service.impl.DtoConverter.toPatients;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientId save(Patient patient) {

        Optional<MongoPatient> optionalPatient = patientRepository.findById(patient.getPatientId().getId());
        MongoPatient mongoPatient;
        if (optionalPatient.isPresent()) {
            mongoPatient = optionalPatient.get();
            mongoPatient.setRegisterNumber(patient.getRegisterNumber());
            mongoPatient.setSisiId(patient.getSisiId());
        } else {
            mongoPatient = new MongoPatient(
                    patient.getPatientId().getId(),
                    patient.getRegisterNumber(),
                    LocalDate.now(),
                    patient.getSisiId()
            );
        }

        patientRepository.save(mongoPatient);

        return patient.getPatientId();
    }

    @Override
    public int count() {
        return (int) patientRepository.count();
    }

    @Override
    public List<Patient> findAll() {
        return toPatients(patientRepository.findAll(Sort.by("createdDate")));
    }

    @Override
    public List<Patient> findAll(int page, int size) {
        return toPatients(patientRepository.findAll(
                PageRequest.of(page, size, Sort.by("createdDate").descending())
        ));
    }

    @Override
    public List<Patient> findAll(LocalDate startDate, LocalDate endDate) {
        return toPatients(patientRepository.findAllByCreatedDateBetween(startDate, endDate, Sort.by("createdDate")));
    }

    @Override
    public List<Patient> findAll(int page, int size, LocalDate startDate, LocalDate endDate) {
        return toPatients(patientRepository.findAllByCreatedDateBetween(
                startDate, endDate,
                PageRequest.of(page, size, Sort.by("createdDate").descending())
        ));
    }

    @Override
    public List<Patient> searchByRegisterNumber(String registerNumberRegex) {
        return toPatients(patientRepository.findAllByRegisterNumberRegex(registerNumberRegex));
    }

    @Override
    public Patient findById(PatientId patientId) throws RepositoryException {
        return isPresent(patientRepository.findById(patientId.getId()));
    }

    @Override
    public Patient findByRegisterNumber(String registerNumber) throws RepositoryException {
        return isPresent(patientRepository.findByRegisterNumber(registerNumber));
    }

    @Override
    public boolean exists(String registerNumber) {
        return patientRepository.existsByRegisterNumber(registerNumber);
    }

    @Override
    public boolean exists(PatientId patientId) {
        return patientRepository.existsById(patientId.getId());
    }

    @Override
    public void delete(PatientId patientId) {
        patientRepository.deleteById(patientId.getId());
    }

    private Patient isPresent(Optional<MongoPatient> optionalPatient) throws RepositoryException {
        MongoPatient patient = optionalPatient.orElseThrow(() -> new RepositoryException(PATIENT_NOT_FOUND));
        return new Patient(
                PatientId.valueOf(patient.getId()),
                patient.getRegisterNumber(),
                patient.getCreatedDate(),
                patient.getSisiId()
        );
    }
}
