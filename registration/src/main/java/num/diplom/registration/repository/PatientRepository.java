package num.diplom.registration.repository;

import num.diplom.registration.repository.model.MongoPatient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<MongoPatient, String> {
    Optional<MongoPatient> findByRegisterNumber(String registerNumber);

    List<MongoPatient> findAllByCreatedDateBetween(LocalDate startDate, LocalDate endDate, Sort sort);

    List<MongoPatient> findAllByCreatedDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<MongoPatient> findAllByRegisterNumberRegex(String registerNumberRegex);

    boolean existsByRegisterNumber(String registerNumber);
}
