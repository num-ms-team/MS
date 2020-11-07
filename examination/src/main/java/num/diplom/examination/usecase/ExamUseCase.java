package num.diplom.examination.usecase;

import num.diplom.base.model.EntityId;
import num.diplom.base.usecase.UseCase;
import num.diplom.examination.model.Exam;
import num.diplom.examination.model.ExamId;
import num.diplom.examination.service.ExamService;
import num.diplom.examination.usecase.model.ExamDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class ExamUseCase<I, O> implements UseCase<I, O> {
    protected final ExamService examService;

    public ExamUseCase(ExamService examService) {
        this.examService = examService;
    }

    protected Exam toExam(ExamDto examDto) {
        return new Exam(
                ExamId.valueOf(UUID.randomUUID().toString()),
                EntityId.valueOf(examDto.getPatientId()),
                examDto.getPatientRegisterNumber(),
                examDto.getSymptom(),
                examDto.getPhysicalState(),
                examDto.getTemperature(),
                examDto.getBloodPressure(),
                examDto.getHeartRate(),
                examDto.getSkinCondition(),
                examDto.getThoracic(),
                examDto.getOther(),
                LocalDateTime.now()
        );
    }

    protected ExamDto toExamDto(Exam exam) {
        return new ExamDto.Builder(
                exam.getExamId().getId(),
                exam.getPatientId().getId(),
                exam.getPatientRegisterNumber(),
                exam.getSymptom()
        )
                .withPhysicalState(exam.getPhysicalState())
                .withTemperature(exam.getTemperature())
                .withBloodPressure(exam.getBloodPressure())
                .withHeartRate(exam.getHeartRate())
                .withSkinCondition(exam.getSkinCondition())
                .withThoracic(exam.getThoracic())
                .withOther(exam.getOther())
                .withExaminedDate(exam.getExaminedDate())
                .build();
    }

    protected List<ExamDto> toExamDtos(List<Exam> exams) {
        return exams.stream().map(this::toExamDto).collect(Collectors.toUnmodifiableList());
    }
}
