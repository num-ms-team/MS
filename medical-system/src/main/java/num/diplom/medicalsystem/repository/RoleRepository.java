package num.diplom.medicalsystem.repository;

import num.diplom.medicalsystem.repository.model.MongoRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends MongoRepository<MongoRole, String> {
    List<MongoRole> findAllByPermissions_Id(String permissionId);
    boolean existsByName(String name);
}
