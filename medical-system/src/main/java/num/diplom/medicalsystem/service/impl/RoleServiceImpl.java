package num.diplom.medicalsystem.service.impl;

import num.diplom.base.exception.RepositoryException;
import num.diplom.medicalsystem.model.PermissionId;
import num.diplom.medicalsystem.model.Role;
import num.diplom.medicalsystem.model.RoleId;
import num.diplom.medicalsystem.repository.RoleRepository;
import num.diplom.medicalsystem.repository.model.MongoRole;
import num.diplom.medicalsystem.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static num.diplom.base.constant.MongoConstants.ROLE_NOT_FOUND;
import static num.diplom.medicalsystem.service.impl.DtoConverter.toMongoRole;
import static num.diplom.medicalsystem.service.impl.DtoConverter.toRole;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(toMongoRole(role));
    }

    @Override
    public Role findById(RoleId roleId) throws RepositoryException {
        Optional<MongoRole> optionalRole = roleRepository.findById(roleId.getId());
        return toRole(optionalRole.orElseThrow(() -> new RepositoryException(ROLE_NOT_FOUND)));
    }

    @Override
    public List<Role> findByPermission(PermissionId permissionId) {
        return roleRepository.findAllByPermissions_Id(permissionId.getId()).stream()
                .map(DtoConverter::toRole)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public void delete(RoleId roleId) {
        roleRepository.deleteById(roleId.getId());
    }
}
