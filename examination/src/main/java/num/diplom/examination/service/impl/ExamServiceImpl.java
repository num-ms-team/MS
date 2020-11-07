package num.diplom.examination.service.impl;

import num.diplom.base.exception.ServiceException;
import num.diplom.base.model.EntityId;
import num.diplom.examination.model.Exam;
import num.diplom.examination.model.ExamId;
import num.diplom.examination.repository.ExamRepository;
import num.diplom.examination.repository.model.MongoExam;
import num.diplom.examination.service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static num.diplom.base.constant.ServiceConstants.EXAM_NOT_FOUND;

@Service
public class ExamServiceImpl implements ExamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);
    private final ExamRepository examRepository;

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(toMongoExam(exam));
    }

    @Override
    public List<Exam> findByPatientId(EntityId patientId) {
        return examRepository.findAllByPatientId(patientId.getId()).stream()
                .map(this::toExam).collect(Collectors.toList());
    }

    @Override
    public List<Exam> findAll(int page, int size, LocalDate startDate, LocalDate endDate) {
        return examRepository.findAllByExaminedDateBetween(
                startDate.atStartOfDay(),
                endDate.atTime(LocalTime.MAX),
                PageRequest.of(page, size, Sort.by("examinedDate").descending())
        ).stream().map(this::toExam).collect(Collectors.toList());
    }

    @Override
    public Exam findById(ExamId examId) throws ServiceException {
        return toExam(examRepository.findById(examId.getId()).orElseThrow(() -> new ServiceException(EXAM_NOT_FOUND)));
    }

    @Override
    public void delete(ExamId examId) {
        examRepository.deleteById(examId.getId());
    }

    private MongoExam toMongoExam(Exam exam) {
        return new MongoExam(
                exam.getExamId().getId(),
                exam.getPatientId().getId(),
                exam.getPatientRegisterNumber(),
                exam.getSymptom(),
                exam.getPhysicalState(),
                exam.getTemperature(),
                exam.getBloodPressure(),
                exam.getHeartRate(),
                exam.getSkinCondition(),
                exam.getThoracic(),
                exam.getOther(),
                exam.getExaminedDate()
        );
    }

    private Exam toExam(MongoExam exam) {
        return new Exam(
                ExamId.valueOf(exam.getId()),
                EntityId.valueOf(exam.getPatientId()),
                exam.getPatientRegisterNumber(),
                exam.getSymptom(),
                exam.getPhysicalState(),
                exam.getTemperature(),
                exam.getBloodPressure(),
                exam.getHeartRate(),
                exam.getSkinCondition(),
                exam.getThoracic(),
                exam.getOther(),
                exam.getExaminedDate()
        );
    }
}
