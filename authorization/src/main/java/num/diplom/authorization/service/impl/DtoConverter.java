package num.diplom.authorization.service.impl;

import num.diplom.authorization.model.*;
import num.diplom.authorization.repository.model.MongoMembership;
import num.diplom.authorization.repository.model.MongoPermission;
import num.diplom.authorization.repository.model.MongoRole;
import num.diplom.authorization.repository.model.MongoUserIdentity;
import num.diplom.base.model.user.UserId;

import java.util.List;
import java.util.stream.Collectors;

public final class DtoConverter {

    private DtoConverter() {
    }

    public static UserIdentity toUserIdentity(MongoUserIdentity mongoUserIdentity) {
        return new UserIdentity(
                UserId.valueOf(mongoUserIdentity.getId()),
                mongoUserIdentity.getUsername(),
                mongoUserIdentity.getPassword()
        );
    }

    public static MongoUserIdentity toMongoUserIdentity(UserIdentity userIdentity) {
        return new MongoUserIdentity(
                userIdentity.getUserId().getId(),
                userIdentity.getUsername(),
                userIdentity.getPassword()
        );
    }

    public static Permission toPermission(MongoPermission mongoPermission) {
        return new Permission(
                PermissionId.valueOf(mongoPermission.getId()),
                mongoPermission.getName(),
                toRoleIds(mongoPermission.getRoles())
        );
    }

    public static MongoPermission toMongoPermission(Permission permission) {
        return new MongoPermission(
                permission.getPermissionId().getId(),
                permission.getName(),
                toRoleStrings(permission.getRoles())
        );
    }

    public static List<RoleId> toRoleIds(List<String> mongoRoles) {
        return mongoRoles.stream().map(RoleId::valueOf).collect(Collectors.toList());
    }

    public static List<String> toRoleStrings(List<RoleId> roleIds) {
        return roleIds.stream().map(RoleId::getId).collect(Collectors.toList());
    }

    public static MongoRole toMongoRole(Role role) {
        return new MongoRole(
                role.getRoleId().getId(),
                role.getName(),
                toMongoPermissions(role.getPermissions())
        );
    }

    public static Role toRole(MongoRole mongoRole) {
        return new Role(
                RoleId.valueOf(mongoRole.getId()),
                mongoRole.getName(),
                toPermissions(mongoRole.getPermissions())
        );
    }

    public static List<Role> toRoles(List<MongoRole> mongoRoles) {
        return mongoRoles.stream().map(DtoConverter::toRole).collect(Collectors.toList());
    }

    public static List<MongoRole> toMongoRoles(List<Role> roles) {
        return roles.stream().map(DtoConverter::toMongoRole).collect(Collectors.toList());
    }

    public static List<MongoPermission> toMongoPermissions(List<Permission> permissions) {
        return permissions.stream().map(DtoConverter::toMongoPermission).collect(Collectors.toList());
    }

    public static List<Permission> toPermissions(List<MongoPermission> mongoPermissions) {
        return mongoPermissions.stream().map(mongoPermission -> new Permission(
                PermissionId.valueOf(mongoPermission.getId()),
                mongoPermission.getName(),
                toRoleIds(mongoPermission.getRoles())
        )).collect(Collectors.toList());
    }

    public static MongoMembership toMongoMembership(Membership membership) {
        return new MongoMembership(
                membership.getMembershipId().getId(),
                membership.getUserId().getId(),
                toMongoRoles(membership.getRoles())
        );
    }

    public static Membership toMembership(MongoMembership mongoMembership) {
        return new Membership(
                MembershipId.valueOf(mongoMembership.getId()),
                UserId.valueOf(mongoMembership.getUserId()),
                toRoles(mongoMembership.getRoles())
        );
    }

    public static List<Membership> toMemberships(List<MongoMembership> mongoMemberships) {
        return mongoMemberships.stream().map(DtoConverter::toMembership).collect(Collectors.toList());
    }
}
