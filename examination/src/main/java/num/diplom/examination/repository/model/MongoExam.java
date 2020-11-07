package num.diplom.examination.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class MongoExam {

    @Id
    private String id;

    @Indexed
    private String patientId;

    @Indexed
    private String patientRegisterNumber;

    private String symptom;
    private String physicalState;
    private float temperature;
    private int bloodPressure;
    private int heartRate;
    private String skinCondition;
    private String thoracic;
    private String other;
    private LocalDateTime examinedDate;

    public MongoExam() {
        // JPA
    }

    public MongoExam(
            String id,
            String patientId,
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
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientRegisterNumber() {
        return patientRegisterNumber;
    }

    public void setPatientRegisterNumber(String patientRegisterNumber) {
        this.patientRegisterNumber = patientRegisterNumber;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
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

    public void setExaminedDate(LocalDateTime examinedDate) {
        this.examinedDate = examinedDate;
    }
}
