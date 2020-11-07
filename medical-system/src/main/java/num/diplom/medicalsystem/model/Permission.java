package num.diplom.medicalsystem.model;

import num.diplom.base.model.Entity;

import java.util.List;

public class Permission implements Entity<Permission> {
    private final PermissionId permissionId;
    private final String name;
    private final List<RoleId> roles;

    public Permission(PermissionId permissionId, String name, List<RoleId> roles) {
        this.permissionId = permissionId;
        this.name = name;
        this.roles = roles;
    }

    public PermissionId getPermissionId() {
        return permissionId;
    }

    public String getName() {
        return name;
    }

    public List<RoleId> getRoles() {
        return roles;
    }

    public void addRole(RoleId roleId) {
        if (!roles.contains(roleId)) {
            roles.add(roleId);
        }
    }

    @Override
    public boolean sameEntityAs(Permission permission) {
        return permission.permissionId.equals(permissionId);
    }
}
