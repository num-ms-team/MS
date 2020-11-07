package num.diplom.registration.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoPatientContact {

    @Id
    private String id;

    private String phoneNumber;
    private String email;
    private String homeAddress;
    private String workAddress;

    public MongoPatientContact() {
    }

    public MongoPatientContact(String id, String phoneNumber, String email, String homeAddress, String workAddress) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
}
