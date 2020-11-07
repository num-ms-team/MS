package num.diplom.examination.repository;

import num.diplom.examination.repository.model.MongoExam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamRepository extends MongoRepository<MongoExam, String> {
    List<MongoExam> findAllByPatientId(String patientId);

    List<MongoExam> findAllByExaminedDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
