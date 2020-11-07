package num.diplom.registration.repository;

import num.diplom.registration.repository.model.MongoPatientInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInfoRepository extends MongoRepository<MongoPatientInfo, String> {
}
