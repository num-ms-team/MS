package num.diplom.medicalsystem.service.impl;

import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.UserId;
import num.diplom.medicalsystem.model.Membership;
import num.diplom.medicalsystem.model.MembershipId;
import num.diplom.medicalsystem.repository.MembershipRepository;
import num.diplom.medicalsystem.service.MembershipService;
import org.springframework.stereotype.Service;

import static num.diplom.base.constant.MongoConstants.MEMBERSHIP_NOT_FOUND;
import static num.diplom.medicalsystem.service.impl.DtoConverter.toMembership;
import static num.diplom.medicalsystem.service.impl.DtoConverter.toMongoMembership;

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
