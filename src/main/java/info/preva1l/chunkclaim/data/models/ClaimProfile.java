package info.preva1l.chunkclaim.data.models;

import java.util.HashMap;
import java.util.UUID;

public interface ClaimProfile {
    HashMap<UUID, ClaimMember> members();
    HashMap<UUID, ProfileGroup> groups();
}
