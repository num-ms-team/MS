package num.diplom.registration.model;

import num.diplom.base.model.EntityId;

public class PatientId extends EntityId {
    public PatientId(String id) {
        super(id);
    }

    public static PatientId valueOf(String id) {
        return new PatientId(id);
    }
}
