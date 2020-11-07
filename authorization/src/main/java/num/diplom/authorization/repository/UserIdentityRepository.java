package num.diplom.authorization.repository;

import num.diplom.authorization.repository.model.MongoUserIdentity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserIdentityRepository extends MongoRepository<MongoUserIdentity, String> {
    Optional<MongoUserIdentity> findByUsername(String username);
}
