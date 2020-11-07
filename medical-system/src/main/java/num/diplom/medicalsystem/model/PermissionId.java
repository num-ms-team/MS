package num.diplom.medicalsystem.model;

import num.diplom.base.model.EntityId;

public class PermissionId extends EntityId {

    public PermissionId(String id) {
        super(id);
    }

    public static PermissionId valueOf(String id) {
        return new PermissionId(id);
    }
}
