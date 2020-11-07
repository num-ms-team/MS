package num.diplom.medicalsystem.repository;

import num.diplom.medicalsystem.repository.model.MongoMembership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends MongoRepository<MongoMembership, String> {
    Optional<MongoMembership> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
