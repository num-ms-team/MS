package num.diplom.registration.controller.model;

public class RestPatient {
    private String id;

    public RestPatient(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
