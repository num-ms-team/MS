package num.diplom.medicalsystem.service;

import num.diplom.base.exception.RepositoryException;
import num.diplom.medicalsystem.model.PermissionId;
import num.diplom.medicalsystem.model.Role;
import num.diplom.medicalsystem.model.RoleId;
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
