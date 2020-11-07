package num.diplom.authorization.service;

import num.diplom.authorization.model.Membership;
import num.diplom.authorization.model.MembershipId;
import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public interface MembershipService {
    void save(Membership membership);

    Membership findById(MembershipId membershipId) throws RepositoryException;

    Membership findByUserId(UserId userId) throws RepositoryException;

    boolean exists(UserId userId);

    void delete(MembershipId membershipId);
}
