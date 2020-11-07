package num.diplom.base.usecase;

import num.diplom.base.model.user.UserId;
import num.diplom.base.service.UserService;
import org.thymeleaf.util.StringUtils;

public class CreateUser implements UseCase<String, UserId> {
    private final UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserId execute(String input) {
        if (!StringUtils.isEmptyOrWhitespace(input)) {
            UserId userId = UserId.valueOf(input);
            userService.create(userId);
            return userId;
        } else {
            return userService.create();
        }
    }
}
