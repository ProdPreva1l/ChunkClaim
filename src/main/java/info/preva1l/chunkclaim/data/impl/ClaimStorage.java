package info.preva1l.chunkclaim.data.impl;


import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.data.models.Claim;
import info.preva1l.chunkclaim.data.models.ClaimMember;
import info.preva1l.chunkclaim.data.models.ClaimProfile;
import info.preva1l.chunkclaim.data.models.ClaimableChunk;
import info.preva1l.chunkclaim.utils.data.StorageHelper;
import org.bson.Document;
import org.bukkit.Chunk;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClaimStorage {
    public Claim getClaim(String server, ClaimableChunk chunk) {
        Document filter = new Document("claimID", StorageHelper.serializeID(server, chunk));
        Document claimDocument = ChunkClaim.i().getCollectionHelper().getCollection("chunkclaim_claim_storage").find(filter).first();
        if (claimDocument == null) {
            return null;
        }
        return new Claim() {
            @Override
            public HashMap<UUID, ClaimMember> members() {
                HashMap<UUID, ClaimMember> claimMembers = new HashMap<>();
                claimDocument.getList("members", UUID.class).forEach(uuid -> {
                    claimMembers.put(uuid, ChunkClaim.i().getClaimMemberStorage().getClaimMember(uuid));
                });
                return claimMembers;
            }

            @Override
            public List<ClaimProfile> profiles() {
                return null;
            }

            @Override
            public List<ClaimableChunk> chunks() {
                return null;
            }

            @Override
            public String claimID() {
                return claimDocument.getString("claimID");
            }
        };
    }
}
