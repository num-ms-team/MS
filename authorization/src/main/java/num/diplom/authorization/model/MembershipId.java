package num.diplom.authorization.model;

import num.diplom.base.model.EntityId;

public class MembershipId extends EntityId {

    public MembershipId(String id) {
        super(id);
    }

    public static MembershipId valueOf(String id) {
        return new MembershipId(id);
    }
}
