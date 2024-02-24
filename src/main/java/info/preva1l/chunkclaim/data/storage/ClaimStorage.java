package info.preva1l.chunkclaim.data.storage;


import info.preva1l.chunkclaim.data.models.Claim;
import info.preva1l.chunkclaim.data.models.ClaimMember;
import info.preva1l.chunkclaim.data.models.ClaimProfile;
import org.bukkit.Chunk;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClaimStorage {
    public Claim getClaim(Chunk chunk) {
        return new Claim() {
            @Override
            public HashMap<UUID, ClaimMember> members() {
                return null;
            }

            @Override
            public List<ClaimProfile> profiles() {
                return null;
            }

            @Override
            public List<Chunk> chunks() {
                return null;
            }
        };
    }
}
