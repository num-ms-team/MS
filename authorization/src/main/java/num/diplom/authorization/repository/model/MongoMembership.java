package num.diplom.authorization.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class MongoMembership {
    @Id
    private String id;

    @Indexed
    private String userId;

    @Indexed
    private List<MongoRole> roles;

    public MongoMembership() {
    }

    public MongoMembership(String id, String userId, List<MongoRole> roles) {
        this.id = id;
        this.userId = userId;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<MongoRole> getRoles() {
        return roles;
    }

    public void setRoles(List<MongoRole> roles) {
        this.roles = roles;
    }

    public void addRole(MongoRole role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }
}
