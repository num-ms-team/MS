package num.diplom.authorization.service.impl;

import num.diplom.authorization.model.Membership;
import num.diplom.authorization.model.Permission;
import num.diplom.authorization.model.Role;
import num.diplom.authorization.model.UserIdentity;
import num.diplom.authorization.service.MembershipService;
import num.diplom.authorization.service.UserIdentityService;
import num.diplom.base.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MSUserDetailsService implements UserDetailsService {

    private final UserIdentityService userIdentityService;
    private final MembershipService membershipService;

    @Autowired
    public MSUserDetailsService(UserIdentityService userIdentityService, MembershipService membershipService) {
        this.userIdentityService = userIdentityService;
        this.membershipService = membershipService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserIdentity userIdentity = userIdentityService.findByUsername(username);
            Membership membership = membershipService.findByUserId(userIdentity.getUserId());

            return new org.springframework.security.core.userdetails.User(
                    userIdentity.getUsername(),
                    userIdentity.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    getAuthorities(membership.getRoles())
            );
        } catch (RepositoryException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        return getGrantedAuthorities(getPermissions(roles));
    }

    private List<String> getPermissions(Collection<Role> roles) {

        List<String> permissions = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            permissions.add(item.getName());
        }
        return permissions;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }
}
