package info.preva1l.chunkclaim.data.helper;

import java.util.UUID;

public abstract class ClaimMember {
    public ClaimProfile getClaimProfile(Claim claim, UUID uuid) {
        claim.getMembers();
        return ret;
    }
}
