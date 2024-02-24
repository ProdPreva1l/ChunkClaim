package info.preva1l.chunkclaim.data.models;

import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface Claim {
    HashMap<UUID, ClaimMember> members();
    List<ClaimProfile> profiles();
    List<Chunk> chunks();
}