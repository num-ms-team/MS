package num.diplom.base.model.user;

import num.diplom.base.model.Entity;

public class User implements Entity<User> {
    private final UserId userId;

    public User(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public boolean sameEntityAs(User user) {
        return user.userId.equals(userId);
    }
}
