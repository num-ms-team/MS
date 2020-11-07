package num.diplom.authorization.repository;

import num.diplom.authorization.repository.model.MongoPermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends MongoRepository<MongoPermission, String> {
    List<MongoPermission> findAllByRolesContaining(String role);

    boolean existsByName(String name);
}
