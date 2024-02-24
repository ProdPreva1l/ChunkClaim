package info.preva1l.chunkclaim.data.helper;

import info.preva1l.chunkclaim.data.storage.ClaimStorage;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class Claim {
    private final ClaimStorage claimStorage = new ClaimStorage();
    public List<UUID> getMembers() {
        claimStorage.getClaim
    }
}
