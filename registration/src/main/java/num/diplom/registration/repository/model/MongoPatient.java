package num.diplom.registration.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class MongoPatient {

    @Id
    private String id;

    @Indexed
    private String registerNumber;

    private LocalDate createdDate;
    private String sisiId;

    public MongoPatient() {
    }

    public MongoPatient(String id, String registerNumber, LocalDate createdDate, String sisiId) {
        this.id = id;
        this.registerNumber = registerNumber;
        this.createdDate = createdDate;
        this.sisiId = sisiId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getSisiId() {
        return sisiId;
    }

    public void setSisiId(String sisiId) {
        this.sisiId = sisiId;
    }
}
