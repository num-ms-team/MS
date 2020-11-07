package num.diplom.authorization.service.impl;

import num.diplom.authorization.model.Membership;
import num.diplom.authorization.model.MembershipId;
import num.diplom.authorization.repository.MembershipRepository;
import num.diplom.authorization.service.MembershipService;
import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.UserId;
import org.springframework.stereotype.Service;

import static num.diplom.authorization.service.impl.DtoConverter.toMembership;
import static num.diplom.authorization.service.impl.DtoConverter.toMongoMembership;
import static num.diplom.base.constant.MongoConstants.MEMBERSHIP_NOT_FOUND;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public void save(Membership membership) {
        membershipRepository.save(toMongoMembership(membership));
    }

    @Override
    public Membership findById(MembershipId membershipId) throws RepositoryException {
        return toMembership(membershipRepository.findById(membershipId.getId()).orElseThrow(() ->
                new RepositoryException(MEMBERSHIP_NOT_FOUND)));
    }

    @Override
    public Membership findByUserId(UserId userId) throws RepositoryException {
        return toMembership(membershipRepository.findByUserId(userId.getId()).orElseThrow(() ->
                new RepositoryException(MEMBERSHIP_NOT_FOUND)));
    }

    @Override
    public boolean exists(UserId userId) {
        return membershipRepository.existsByUserId(userId.getId());
    }

    @Override
    public void delete(MembershipId membershipId) {
        membershipRepository.deleteById(membershipId.getId());
    }
}
