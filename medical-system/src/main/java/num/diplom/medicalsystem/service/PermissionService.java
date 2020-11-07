package num.diplom.medicalsystem.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.medicalsystem.model.Permission;
import num.diplom.medicalsystem.model.PermissionId;
import num.diplom.medicalsystem.model.RoleId;
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
