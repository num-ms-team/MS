package num.diplom.authorization.repository;

import num.diplom.authorization.repository.model.MongoMembership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends MongoRepository<MongoMembership, String> {
    Optional<MongoMembership> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
