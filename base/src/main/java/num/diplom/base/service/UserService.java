package num.diplom.base.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.User;
import num.diplom.base.model.user.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserId create();

    void create(UserId userId);

    List<User> findAll();

    User findById(UserId userId) throws RepositoryException;

    boolean exists(UserId userId);

    void delete(UserId userId);
}
