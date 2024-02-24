package info.preva1l.chunkclaim.data.models;

import java.util.HashMap;
import java.util.UUID;

public interface ClaimMember {
    HashMap<UUID, ClaimProfile> profiles();
}
