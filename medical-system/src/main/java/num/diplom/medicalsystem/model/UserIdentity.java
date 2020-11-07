package num.diplom.medicalsystem.model;

import num.diplom.base.model.Entity;
import num.diplom.base.model.user.UserId;

public class UserIdentity implements Entity<UserIdentity> {
    private final UserId userId;
    private String username;
    private String password;

    public UserIdentity(UserId userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean sameEntityAs(UserIdentity userIdentity) {
        return userIdentity.userId.equals(userId);
    }
}
