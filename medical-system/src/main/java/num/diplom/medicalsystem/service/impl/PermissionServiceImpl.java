package num.diplom.medicalsystem.service.impl;

import num.diplom.base.exception.RepositoryException;
import num.diplom.medicalsystem.model.Permission;
import num.diplom.medicalsystem.model.PermissionId;
import num.diplom.medicalsystem.model.RoleId;
import num.diplom.medicalsystem.repository.PermissionRepository;
import num.diplom.medicalsystem.repository.model.MongoPermission;
import num.diplom.medicalsystem.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static num.diplom.base.constant.MongoConstants.PERMISSION_NOT_FOUND;
import static num.diplom.medicalsystem.service.impl.DtoConverter.toMongoPermission;
import static num.diplom.medicalsystem.service.impl.DtoConverter.toPermission;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void save(Permission permission) {
        permissionRepository.save(toMongoPermission(permission));
    }

    @Override
    public Permission findById(PermissionId permissionId) throws RepositoryException {
        Optional<MongoPermission> optionalPermission = permissionRepository.findById(permissionId.getId());
        return toPermission(optionalPermission.orElseThrow(() -> new RepositoryException(PERMISSION_NOT_FOUND)));
    }

    @Override
    public List<Permission> findByRoleId(RoleId roleId) {
        return permissionRepository.findAllByRolesContaining(roleId.getId()).stream()
                .map(DtoConverter::toPermission)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(String name) {
        return permissionRepository.existsByName(name);
    }

    @Override
    public void delete(PermissionId permissionId) {
        permissionRepository.deleteById(permissionId.getId());
    }
}
