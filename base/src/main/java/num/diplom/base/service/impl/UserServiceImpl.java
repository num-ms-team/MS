package num.diplom.base.service.impl;

import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.User;
import num.diplom.base.model.user.UserId;
import num.diplom.base.repository.UserRepository;
import num.diplom.base.repository.model.MongoUser;
import num.diplom.base.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static num.diplom.base.constant.MongoConstants.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserId create() {
        UserId userId = UserId.valueOf(UUID.randomUUID().toString());
        userRepository.save(new MongoUser(userId.getId()));
        return userId;
    }

    @Override
    public void create(UserId userId) {
        userRepository.save(new MongoUser(userId.getId()));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new User(UserId.valueOf(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public User findById(UserId userId) throws RepositoryException {
        Optional<MongoUser> optionalUser = userRepository.findById(userId.getId());
        MongoUser user = optionalUser.orElseThrow(() -> new RepositoryException(USER_NOT_FOUND));
        return new User(UserId.valueOf(user.getId()));
    }

    @Override
    public boolean exists(UserId userId) {
        return userRepository.existsById(userId.getId());
    }

    @Override
    public void delete(UserId userId) {
        userRepository.deleteById(userId.getId());
    }
}
