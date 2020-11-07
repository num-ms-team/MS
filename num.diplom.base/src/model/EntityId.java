package model;

public abstract class EntityId {
    private final String id;

    public EntityId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof EntityId && ((EntityId) other).id.equals(id);
    }
}
