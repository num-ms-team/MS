package num.diplom.base.model;

import java.io.Serializable;
import java.util.Objects;

public class EntityId implements Serializable {
    private final String id;

    public EntityId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static EntityId valueOf(String id) {
        return new EntityId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityId)) return false;
        EntityId entityId = (EntityId) o;
        return getId().equals(entityId.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return id;
    }
}
