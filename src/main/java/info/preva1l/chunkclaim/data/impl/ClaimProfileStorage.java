package info.preva1l.chunkclaim.data.impl;

import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.data.models.Claim;
import info.preva1l.chunkclaim.data.models.ClaimMember;
import info.preva1l.chunkclaim.data.models.ClaimProfile;
import info.preva1l.chunkclaim.data.models.ProfileGroup;
import org.bson.Document;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public class ClaimProfileStorage {
    public CompletableFuture<ClaimProfile> getClaimProfile(Claim claim) {
        return CompletableFuture.supplyAsync(() -> {
            Document filter = new Document("chunkID", claim.claimID());
            Document profileDocument = ChunkClaim.i().getCollectionHelper().getCollection("chunkclaim_profile_storage").find(filter).first();
            return new ClaimProfile() {
                @Override
                public HashMap<UUID, ClaimMember> members() {
                    HashMap<UUID, ClaimMember> hashMap = new HashMap<>();
                    return hashMap;
                }

                @Override
                public HashMap<UUID, ProfileGroup> groups() {
                    return null;
                }
            };
        });
    }
}
