package num.diplom.medicalsystem.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.UserId;
import num.diplom.medicalsystem.model.UserIdentity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserIdentityService {
    void save(UserIdentity userIdentity);
    List<UserIdentity> findAll();
    UserIdentity findById(UserId userId) throws RepositoryException;
    UserIdentity findByUsername(String username) throws RepositoryException;
    void delete(UserId userId);
}
