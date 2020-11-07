package num.diplom.medicalsystem.model;

import num.diplom.base.model.Entity;

import java.util.List;

public class Role implements Entity<Role> {
    private final RoleId roleId;
    private final String name;
    private List<Permission> permissions;

    public Role(RoleId roleId, String name, List<Permission> permissions) {
        this.roleId = roleId;
        this.name = name;
        this.permissions = permissions;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);

        if (!permission.getRoles().contains(roleId)) {
            permission.addRole(roleId);
        }
    }

    @Override
    public boolean sameEntityAs(Role role) {
        return role.roleId.equals(roleId);
    }
}
