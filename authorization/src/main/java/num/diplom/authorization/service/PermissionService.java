package num.diplom.authorization.service;

import num.diplom.authorization.model.Permission;
import num.diplom.authorization.model.PermissionId;
import num.diplom.authorization.model.RoleId;
import num.diplom.base.exception.RepositoryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    void save(Permission permission);

    Permission findById(PermissionId permissionId) throws RepositoryException;

    List<Permission> findByRoleId(RoleId roleId);

    boolean exists(String name);

    void delete(PermissionId permissionId);
}
