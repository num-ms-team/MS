package num.diplom.examination.service;

import num.diplom.base.exception.ServiceException;
import num.diplom.base.model.EntityId;
import num.diplom.examination.model.Exam;
import num.diplom.examination.model.ExamId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ExamService {
    void save(Exam exam);

    List<Exam> findByPatientId(EntityId patientId);

    List<Exam> findAll(int page, int size, LocalDate startDate, LocalDate endDate);

    Exam findById(ExamId examId) throws ServiceException;

    void delete(ExamId examId);
}
