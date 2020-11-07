package num.diplom.authorization.service.impl;

import num.diplom.authorization.model.UserIdentity;
import num.diplom.authorization.repository.UserIdentityRepository;
import num.diplom.authorization.repository.model.MongoUserIdentity;
import num.diplom.authorization.service.UserIdentityService;
import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static num.diplom.authorization.service.impl.DtoConverter.toMongoUserIdentity;
import static num.diplom.authorization.service.impl.DtoConverter.toUserIdentity;
import static num.diplom.base.constant.MongoConstants.USER_IDENTITY_NOT_FOUND;

@Service
public class UserIdentityServiceImpl implements UserIdentityService {

    private final UserIdentityRepository userIdentityRepository;

    public UserIdentityServiceImpl(UserIdentityRepository userIdentityRepository) {
        this.userIdentityRepository = userIdentityRepository;
    }

    @Override
    public void save(UserIdentity userIdentity) {
        userIdentityRepository.save(toMongoUserIdentity(userIdentity));
    }

    @Override
    public List<UserIdentity> findAll() {
        return userIdentityRepository.findAll().stream()
                .map(DtoConverter::toUserIdentity)
                .collect(Collectors.toList());
    }

    @Override
    public UserIdentity findById(UserId userId) throws RepositoryException {
        Optional<MongoUserIdentity> optionalUserIdentity = userIdentityRepository.findById(userId.getId());
        return toUserIdentity(optionalUserIdentity.orElseThrow(() ->
                new RepositoryException(USER_IDENTITY_NOT_FOUND)));
    }

    @Override
    public UserIdentity findByUsername(String username) throws RepositoryException {
        Optional<MongoUserIdentity> optionalUserIdentity = userIdentityRepository.findByUsername(username);
        return toUserIdentity(optionalUserIdentity.orElseThrow(() ->
                new RepositoryException(USER_IDENTITY_NOT_FOUND)));
    }

    @Override
    public void delete(UserId userId) {

    }
}
