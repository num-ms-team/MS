package num.diplom.medicalsystem.model;

import num.diplom.base.model.EntityId;

public class RoleId extends EntityId {

    public RoleId(String id) {
        super(id);
    }

    public static RoleId valueOf(String id) {
        return new RoleId(id);
    }
}
