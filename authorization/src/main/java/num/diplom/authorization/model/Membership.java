package num.diplom.authorization.model;

import num.diplom.base.model.Entity;
import num.diplom.base.model.user.UserId;

import java.util.List;

public class Membership implements Entity<Membership> {
    private final MembershipId membershipId;
    private final UserId userId;
    private List<Role> roles;

    public Membership(MembershipId membershipId, UserId userId, List<Role> roles) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.roles = roles;
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }

    public UserId getUserId() {
        return userId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public boolean sameEntityAs(Membership membership) {
        return membership.membershipId.equals(membershipId);
    }
}
