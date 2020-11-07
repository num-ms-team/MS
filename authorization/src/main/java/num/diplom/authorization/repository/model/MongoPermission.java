package num.diplom.authorization.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class MongoPermission {

    @Id
    private String id;

    @Indexed
    private String name;

    @Indexed
    private List<String> roles;

    public MongoPermission() {
    }

    public MongoPermission(String id, String name, List<String> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }
}
