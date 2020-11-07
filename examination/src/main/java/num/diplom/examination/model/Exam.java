package num.diplom.examination.model;

import num.diplom.base.model.Entity;
import num.diplom.base.model.EntityId;

import java.time.LocalDateTime;

public class Exam implements Entity<Exam> {

    private final ExamId examId;
    private final EntityId patientId;
    private final String patientRegisterNumber;
    private final String symptom;
    private String physicalState;
    private float temperature;
    private int bloodPressure;
    private int heartRate;
    private String skinCondition;
    private String thoracic;
    private String other;

    private final LocalDateTime examinedDate;

    public Exam(ExamId examId, EntityId patientId, String patientRegisterNumber, String symptom) {
        this.examId = examId;
        this.patientId = patientId;
        this.patientRegisterNumber = patientRegisterNumber;
        this.symptom = symptom;
        examinedDate = LocalDateTime.now();
    }

    public Exam(
            ExamId examId,
            EntityId patientId,
            String patientRegisterNumber,
            String symptom,
            String physicalState,
            float temperature,
            int bloodPressure,
            int heartRate,
            String skinCondition,
            String thoracic,
            String other,
            LocalDateTime examinedDate
    ) {
        this.examId = examId;
        this.patientId = patientId;
        this.patientRegisterNumber = patientRegisterNumber;
        this.symptom = symptom;
        this.physicalState = physicalState;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.skinCondition = skinCondition;
        this.thoracic = thoracic;
        this.other = other;
        this.examinedDate = examinedDate;
    }

    public ExamId getExamId() {
        return examId;
    }

    public EntityId getPatientId() {
        return patientId;
    }

    public String getPatientRegisterNumber() {
        return patientRegisterNumber;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getPhysicalState() {
        return physicalState;
    }

    public void setPhysicalState(String physicalState) {
        this.physicalState = physicalState;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getSkinCondition() {
        return skinCondition;
    }

    public void setSkinCondition(String skinCondition) {
        this.skinCondition = skinCondition;
    }

    public String getThoracic() {
        return thoracic;
    }

    public void setThoracic(String thoracic) {
        this.thoracic = thoracic;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public LocalDateTime getExaminedDate() {
        return examinedDate;
    }

    @Override
    public boolean sameEntityAs(Exam other) {
        return other.examId.equals(examId);
    }
}
