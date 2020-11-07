package num.diplom.examination.usecase;

import num.diplom.base.exception.UseCaseException;
import num.diplom.examination.model.Exam;
import num.diplom.examination.model.ExamId;
import num.diplom.examination.service.ExamService;
import num.diplom.examination.usecase.model.ExamDto;
import org.thymeleaf.util.Validate;

import static num.diplom.base.constant.RestConstants.INVALID_INPUT;
import static num.diplom.base.constant.UseCaseConstants.PATIENT_ID_CANNOT_BE_NULL_OR_EMPTY;

public class CreateExam extends ExamUseCase<ExamDto, ExamId> {

    public CreateExam(ExamService examService) {
        super(examService);
    }

    @Override
    public ExamId execute(ExamDto input) throws UseCaseException {
        try {
            Validate.notNull(input, INVALID_INPUT);
            Validate.notEmpty(input.getPatientId(), PATIENT_ID_CANNOT_BE_NULL_OR_EMPTY);
        } catch (IllegalArgumentException e) {
            throw new UseCaseException(e.getMessage(), e);
        }
        Exam exam = toExam(input);
        examService.save(exam);

        return exam.getExamId();
    }
}
