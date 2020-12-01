package num.diplom.registration.controller;

import num.diplom.base.exception.UseCaseException;
import num.diplom.base.usecase.filter.DateFilter;
import num.diplom.base.usecase.filter.Filter;
import num.diplom.base.usecase.filter.TableFilter;
import num.diplom.registration.controller.model.RestCount;
import num.diplom.registration.controller.model.RestPatient;
import num.diplom.registration.model.PatientId;
import num.diplom.registration.service.PatientContactService;
import num.diplom.registration.service.PatientInfoService;
import num.diplom.registration.service.PatientService;
import num.diplom.registration.usecase.*;
import num.diplom.registration.usecase.model.PatientDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {
    private static final String LOCAL_DATE_PATTERN = "MM/d/yyyy";
    private final PatientService patientService;
    private final PatientInfoService patientInfoService;
    private final PatientContactService patientContactService;

    public PatientController(
            PatientService patientService,
            PatientInfoService patientInfoService,
            PatientContactService patientContactService
    ) {
        this.patientService = patientService;
        this.patientInfoService = patientInfoService;
        this.patientContactService = patientContactService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) @DateTimeFormat(pattern = LOCAL_DATE_PATTERN) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = LOCAL_DATE_PATTERN) LocalDate endDate
    ) {
        Filter filter = new Filter();

        if (page != null && size != null) {
            filter.setTableFilter(new TableFilter(page, size));
        }

        if (startDate != null && endDate != null) {
            filter.setDateFilter(new DateFilter(startDate, endDate));
        }

        GetPatients getPatients = new GetPatients(patientService, patientInfoService, patientContactService);
        return ResponseEntity.ok(getPatients.execute(filter));
    }

    @PostMapping
    public ResponseEntity<RestPatient> create(@RequestBody PatientDto body) {
        CreatePatient createPatient = new CreatePatient(patientService, patientInfoService, patientContactService);
        try {
            PatientId patientId = createPatient.execute(body);
            return ResponseEntity.created(URI.create(String.format("patients/%s", patientId.getId())))
                    .body(new RestPatient(patientId.getId()));
        } catch (UseCaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> findById(@PathVariable String patientId) {
        GetPatient getPatient = new GetPatient(patientService, patientInfoService, patientContactService);
        try {
            return ResponseEntity.ok(getPatient.execute(patientId));
        } catch (UseCaseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/registers")
    public ResponseEntity<List<String>> findAllRegisters() {
        GetAllRegisters getAllRegisters = new GetAllRegisters(patientService, patientInfoService, patientContactService);
        return ResponseEntity.ok(getAllRegisters.execute(null));
    }

    @PostMapping("/patient")
    public ResponseEntity<PatientDto> findByRegisterNumber(@RequestBody String registerNumber) {
        GetPatientByRegisterNumber getPatientByRegisterNumber = new GetPatientByRegisterNumber(patientService, patientInfoService, patientContactService);
        try {
            return ResponseEntity.ok(getPatientByRegisterNumber.execute(registerNumber));
        } catch (UseCaseException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<RestCount> count() {
        GetPatientsCount getPatientsCount = new GetPatientsCount(patientService, patientInfoService, patientContactService);
        return ResponseEntity.ok(new RestCount(getPatientsCount.execute(null)));
    }
}
