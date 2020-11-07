package num.diplom.base.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoUser {
    @Id
    private String id;

    public MongoUser() {
    }

    public MongoUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
