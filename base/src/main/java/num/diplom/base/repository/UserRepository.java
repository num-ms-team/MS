package num.diplom.base.repository;

import num.diplom.base.repository.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<MongoUser, String> {
}
