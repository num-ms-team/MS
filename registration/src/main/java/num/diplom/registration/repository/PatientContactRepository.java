package num.diplom.registration.repository;

import num.diplom.registration.repository.model.MongoPatientContact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientContactRepository extends MongoRepository<MongoPatientContact, String> {
}
