package num.diplom.examination.usecase;

import num.diplom.base.exception.ServiceException;
import num.diplom.base.exception.UseCaseException;
import num.diplom.examination.model.ExamId;
import num.diplom.examination.service.ExamService;
import num.diplom.examination.usecase.model.ExamDto;
import org.thymeleaf.util.Validate;

import static num.diplom.base.constant.UseCaseConstants.INPUT_CANNOT_BE_NULL_OR_EMPTY;

public class GetExamById extends ExamUseCase<String, ExamDto> {

    public GetExamById(ExamService examService) {
        super(examService);
    }

    @Override
    public ExamDto execute(String input) throws UseCaseException {
        try {
            Validate.notEmpty(input, INPUT_CANNOT_BE_NULL_OR_EMPTY);
            return toExamDto(examService.findById(ExamId.valueOf(input)));
        } catch (ServiceException | IllegalArgumentException e) {
            throw new UseCaseException(e.getMessage(), e);
        }
    }
}
