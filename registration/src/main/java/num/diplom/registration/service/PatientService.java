package num.diplom.registration.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.registration.model.Patient;
import num.diplom.registration.model.PatientId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PatientService {
    /**
     * Creates patient
     *
     * @param patient creating patient
     * @return created patient's ID
     */
    PatientId save(Patient patient);

    /**
     * Total count of the patients
     *
     * @return integer
     */
    int count();

    /**
     * Finds all the patients
     *
     * @return list of patient objects
     */
    List<Patient> findAll();

    /**
     * Finds patients with table filter
     *
     * @param page the page number
     * @param size the size of the page
     * @return list of patient objects
     */
    List<Patient> findAll(int page, int size);

    /**
     * Finds patients with date filter
     *
     * @param startDate the starting date of the patients created
     * @param endDate   the ending date of the patients created
     * @return list of patient objects
     */
    List<Patient> findAll(LocalDate startDate, LocalDate endDate);

    /**
     * Finds patients with all filter
     *
     * @param page      the page number
     * @param size      the page size
     * @param startDate the starting date of the patients created
     * @param endDate   the ending date of the patients created
     * @return list of patient objects
     */
    List<Patient> findAll(int page, int size, LocalDate startDate, LocalDate endDate);

    /**
     * Search by register number
     *
     * @param registerNumberRegex the regex of the register number
     * @return list of patient objects
     */
    List<Patient> searchByRegisterNumber(String registerNumberRegex);

    /**
     * Searches patient by ID
     *
     * @param patientId the ID of the searching patient
     * @return patient object
     * @throws RepositoryException if not found
     */
    Patient findById(PatientId patientId) throws RepositoryException;

    /**
     * Searches patient by register number
     *
     * @param registerNumber the register number of the searching patient
     * @return patient object
     * @throws RepositoryException if not found
     */
    Patient findByRegisterNumber(String registerNumber) throws RepositoryException;

    /**
     * Checks if the patient with register number already registered
     *
     * @param registerNumber the checking register number
     * @return true if registered; otherwise false
     */
    boolean exists(String registerNumber);

    /**
     * Checks if the patient with ID already registered
     *
     * @param patientId the checking patient ID
     * @return true if registered; otherwise false
     */
    boolean exists(PatientId patientId);

    /**
     * Deletes patient with the given ID
     *
     * @param patientId deleting patient's ID
     */
    void delete(PatientId patientId);
}
