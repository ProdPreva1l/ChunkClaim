package info.preva1l.chunkclaim.data.impl;

import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.data.models.ClaimMember;
import info.preva1l.chunkclaim.data.models.ClaimProfile;
import org.bson.Document;

import java.util.HashMap;
import java.util.UUID;

public class ClaimMemberStorage {
    public ClaimMember getClaimMember(UUID uuid) {
        Document filter = new Document("player", uuid.toString());
        Document memberDocument = ChunkClaim.i().getCollectionHelper().getCollection("chunkclaim_member_storage").find(filter).first();
        return new ClaimMember() {
            @Override
            public HashMap<UUID, ClaimProfile> profiles() {
                HashMap<UUID, ClaimProfile> hashMap = new HashMap<>();
                return hashMap;
            }
        };
    }
}
