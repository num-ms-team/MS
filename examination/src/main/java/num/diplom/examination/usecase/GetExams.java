package num.diplom.examination.usecase;

import num.diplom.base.usecase.filter.Filter;
import num.diplom.examination.service.ExamService;
import num.diplom.examination.usecase.model.ExamDto;

import java.util.List;

public class GetExams extends ExamUseCase<Filter, List<ExamDto>> {
    public GetExams(ExamService examService) {
        super(examService);
    }

    @Override
    public List<ExamDto> execute(Filter input) {
        return toExamDtos(examService.findAll(
                input.getTableFilter().getPage(),
                input.getTableFilter().getSize(),
                input.getDateFilter().getStartDate(),
                input.getDateFilter().getEndDate()
        ));
    }
}
