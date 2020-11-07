package num.diplom.authorization.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class MongoRole {

    @Id
    private String id;

    private String name;

    @Indexed
    private List<MongoPermission> permissions;

    public MongoRole() {
    }

    public MongoRole(String id, String name, List<MongoPermission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
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

    public List<MongoPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<MongoPermission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(MongoPermission permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }
}
