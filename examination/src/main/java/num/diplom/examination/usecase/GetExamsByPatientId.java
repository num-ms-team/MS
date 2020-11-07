package num.diplom.examination.usecase;

import num.diplom.base.exception.UseCaseException;
import num.diplom.base.model.EntityId;
import num.diplom.examination.model.Exam;
import num.diplom.examination.service.ExamService;
import num.diplom.examination.usecase.model.ExamDto;
import org.thymeleaf.util.Validate;

import java.util.Collections;
import java.util.List;

import static num.diplom.base.constant.UseCaseConstants.INPUT_CANNOT_BE_NULL_OR_EMPTY;

public class GetExamsByPatientId extends ExamUseCase<String, List<ExamDto>> {

    public GetExamsByPatientId(ExamService examService) {
        super(examService);
    }

    @Override
    public List<ExamDto> execute(String input) throws UseCaseException {
        try {
            Validate.notEmpty(input, INPUT_CANNOT_BE_NULL_OR_EMPTY);
        } catch (IllegalArgumentException e) {
            throw new UseCaseException(e.getMessage(), e);
        }
        List<Exam> exams = examService.findByPatientId(EntityId.valueOf(input));
        if (exams == null || exams.isEmpty()) {
            return Collections.emptyList();
        }
        return toExamDtos(exams);
    }
}
