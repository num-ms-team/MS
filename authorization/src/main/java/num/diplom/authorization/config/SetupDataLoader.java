package num.diplom.authorization.config;

import num.diplom.authorization.model.*;
import num.diplom.authorization.service.MembershipService;
import num.diplom.authorization.service.PermissionService;
import num.diplom.authorization.service.RoleService;
import num.diplom.authorization.service.UserIdentityService;
import num.diplom.base.exception.RepositoryException;
import num.diplom.base.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserIdentityService userIdentityService;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final MembershipService membershipService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(
            UserIdentityService userIdentityService,
            PermissionService permissionService,
            RoleService roleService,
            MembershipService membershipService,
            PasswordEncoder passwordEncoder
    ) {
        this.userIdentityService = userIdentityService;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.membershipService = membershipService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        String userId = "admin";

        userIdentityService.save(new UserIdentity(
                UserId.valueOf(userId),
                "developer",
                passwordEncoder.encode("somepass")
        ));

        Permission readPermission = createPermissionIfNotFound("READ_PERMISSION");
        Permission writePermission = createPermissionIfNotFound("WRITE_PERMISSION");

        List<Permission> adminPermissions = Arrays.asList(readPermission, writePermission);

        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPermissions);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPermission));

        createMembershipIfNotFound(UserId.valueOf(userId), Collections.singletonList(adminRole));

        alreadySetup = true;
    }

    @Transactional
    Permission createPermissionIfNotFound(String name) {
        Permission permission;
        try {
            permission = permissionService.findById(PermissionId.valueOf(name));
        } catch (RepositoryException e) {
            permission = new Permission(PermissionId.valueOf(name), name, new ArrayList<>());
            permissionService.save(permission);
        }
        return permission;
    }

    @Transactional
    Role createRoleIfNotFound(String name, List<Permission> permissions) {
        Role role;
        try {
            role = roleService.findById(RoleId.valueOf(name));
        } catch (RepositoryException e) {
            role = new Role(RoleId.valueOf(name), name, permissions);
            roleService.save(role);
        }
        return role;
    }

    @Transactional
    Membership createMembershipIfNotFound(UserId userId, List<Role> roles) {
        Membership membership;
        try {
            membership = membershipService.findByUserId(userId);
        } catch (RepositoryException e) {
            membership = new Membership(MembershipId.valueOf(userId.getId()), userId, roles);
            membershipService.save(membership);
        }
        return membership;
    }
}
