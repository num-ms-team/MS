package num.diplom.medicalsystem.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.UserId;
import num.diplom.medicalsystem.model.Membership;
import num.diplom.medicalsystem.model.MembershipId;
import org.springframework.stereotype.Service;

@Service
public interface MembershipService {
    void save(Membership membership);
    Membership findById(MembershipId membershipId) throws RepositoryException;
    Membership findByUserId(UserId userId) throws RepositoryException;
    boolean exists(UserId userId);
    void delete(MembershipId membershipId);
}
