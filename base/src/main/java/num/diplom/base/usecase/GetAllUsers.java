package num.diplom.base.usecase;

import num.diplom.base.model.user.User;
import num.diplom.base.service.UserService;

import java.util.List;

public class GetAllUsers implements UseCase<Void, List<User>> {
    private final UserService userService;

    public GetAllUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> execute(Void input) {
        return userService.findAll();
    }
}
