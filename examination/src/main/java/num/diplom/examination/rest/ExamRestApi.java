package num.diplom.examination.rest;

import num.diplom.base.exception.UseCaseException;
import num.diplom.base.usecase.filter.DateFilter;
import num.diplom.base.usecase.filter.Filter;
import num.diplom.base.usecase.filter.TableFilter;
import num.diplom.examination.model.ExamId;
import num.diplom.examination.rest.model.RestExam;
import num.diplom.examination.service.ExamService;
import num.diplom.examination.usecase.CreateExam;
import num.diplom.examination.usecase.GetExamById;
import num.diplom.examination.usecase.GetExams;
import num.diplom.examination.usecase.GetExamsByPatientId;
import num.diplom.examination.usecase.model.ExamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.Validate;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static num.diplom.base.constant.RestConstants.INVALID_INPUT;

@RestController
@RequestMapping("exams")
public class ExamRestApi {
    private static final String LOCAL_DATE_PATTERN = "MM/d/yyyy";
    private final ExamService examService;

    @Autowired
    public ExamRestApi(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<RestExam> createExam(@RequestBody ExamDto body) {
        Validate.notNull(body, INVALID_INPUT);
        CreateExam createExam = new CreateExam(examService);
        try {
            ExamId examId = createExam.execute(body);
            return ResponseEntity.created(URI.create(String.format("exams/%s", examId.getId())))
                    .body(new RestExam(examId.getId()));
        } catch (UseCaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ExamDto>> getAll(@PathVariable String patientId) {
        Validate.notEmpty(patientId, INVALID_INPUT);
        GetExamsByPatientId getExamsByPatientId = new GetExamsByPatientId(examService);
        try {
            return ResponseEntity.ok(getExamsByPatientId.execute(patientId));
        } catch (UseCaseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ExamDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam @DateTimeFormat(pattern = LOCAL_DATE_PATTERN) LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = LOCAL_DATE_PATTERN) LocalDate endDate
    ) {
        Filter filter = new Filter(new DateFilter(startDate, endDate), new TableFilter(page, size));
        GetExams getExams = new GetExams(examService);
        return ResponseEntity.ok(getExams.execute(filter));
    }

    @GetMapping("/{examId}")
    public ResponseEntity<ExamDto> getById(@PathVariable String examId) {
        GetExamById getExamById = new GetExamById(examService);
        try {
            return ResponseEntity.ok(getExamById.execute(examId));
        } catch (UseCaseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
