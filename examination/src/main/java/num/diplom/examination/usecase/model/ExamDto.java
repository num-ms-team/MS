package num.diplom.examination.usecase.model;

import java.time.LocalDateTime;

public class ExamDto {

    private final String id;
    private final String patientId;
    private final String patientRegisterNumber;
    private final String symptom;
    private String physicalState;
    private float temperature;
    private int bloodPressure;
    private int heartRate;
    private String skinCondition;
    private String thoracic;
    private String other;
    private LocalDateTime examinedDate;

    public ExamDto(String id, String patientId, String patientRegisterNumber, String symptom) {
        this.id = id;
        this.patientId = patientId;
        this.patientRegisterNumber = patientRegisterNumber;
        this.symptom = symptom;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
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

    public float getTemperature() {
        return temperature;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public String getSkinCondition() {
        return skinCondition;
    }

    public String getThoracic() {
        return thoracic;
    }

    public String getOther() {
        return other;
    }

    public LocalDateTime getExaminedDate() {
        return examinedDate;
    }

    public static class Builder {
        private final String id;
        private final String patientId;
        private final String patientRegisterNumber;
        private final String symptom;
        private String physicalState;
        private float temperature;
        private int bloodPressure;
        private int heartRate;
        private String skinCondition;
        private String thoracic;
        private String other;
        private LocalDateTime examinedDate;

        public Builder(String id, String patientId, String patientRegisterNumber, String symptom) {
            this.id = id;
            this.patientId = patientId;
            this.patientRegisterNumber = patientRegisterNumber;
            this.symptom = symptom;
        }

        public Builder withPhysicalState(String physicalState) {
            this.physicalState = physicalState;
            return this;
        }

        public Builder withTemperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder withBloodPressure(int bloodPressure) {
            this.bloodPressure = bloodPressure;
            return this;
        }

        public Builder withHeartRate(int heartRate) {
            this.heartRate = heartRate;
            return this;
        }

        public Builder withSkinCondition(String skinCondition) {
            this.skinCondition = skinCondition;
            return this;
        }

        public Builder withThoracic(String thoracic) {
            this.thoracic = thoracic;
            return this;
        }

        public Builder withOther(String other) {
            this.other = other;
            return this;
        }

        public Builder withExaminedDate(LocalDateTime examinedDate) {
            this.examinedDate = examinedDate;
            return this;
        }

        public ExamDto build() {
            ExamDto output = new ExamDto(id, patientId, patientRegisterNumber, symptom);

            output.physicalState = physicalState;
            output.temperature = temperature;
            output.bloodPressure = bloodPressure;
            output.heartRate = heartRate;
            output.skinCondition = skinCondition;
            output.thoracic = thoracic;
            output.other = other;
            output.examinedDate = examinedDate;

            return output;
        }
    }
}
