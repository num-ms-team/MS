package num.diplom.authorization.service;

import num.diplom.authorization.model.PermissionId;
import num.diplom.authorization.model.Role;
import num.diplom.authorization.model.RoleId;
import num.diplom.base.exception.RepositoryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    void save(Role role);

    Role findById(RoleId roleId) throws RepositoryException;

    List<Role> findByPermission(PermissionId permissionId);

    boolean exists(String name);

    void delete(RoleId roleId);
}
