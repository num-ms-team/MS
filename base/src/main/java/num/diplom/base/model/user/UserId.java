package num.diplom.base.model.user;

import num.diplom.base.model.EntityId;

public class UserId extends EntityId {

    public UserId(String id) {
        super(id);
    }

    public static UserId valueOf(String id) {
        return new UserId(id);
    }
}
