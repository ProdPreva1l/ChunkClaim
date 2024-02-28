package info.preva1l.chunkclaim.guis.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Chunk;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class GuiUtil {
    public List<Chunk> getNearByChunksRelativeToPlayerAndMenu(Player player) {
        List<Chunk> chunkList = new LinkedList<>();
        BlockFace facing = player.getFacing();
        int playerChunkX = player.getChunk().getX();
        int playerChunkZ = player.getChunk().getZ();
        switch (facing) {
            case NORTH:
                for (int z = -2; z <= 2; z++) { // normal z-order for SOUTH
                    for (int x = -4; x <= 4; x++) {
                        int chunkX = playerChunkX + x, chunkZ = playerChunkZ + z;
                        chunkList.add(player.getWorld().getChunkAt(chunkX, chunkZ));
                    }
                }
                break;
            case EAST:
                for (int x = 2; x >= -2; x--) {
                    for (int z = -4; z <= 4; z++) {
                        int chunkX = playerChunkX + x, chunkZ = playerChunkZ + z;
                        chunkList.add(player.getWorld().getChunkAt(chunkX, chunkZ));
                    }
                }
                break;
            case SOUTH:
                for (int z = 2; z >= -2; z--){ // reverse z-order for NORTH
                    for (int x = 4; x >= -4; x --) {
                        int chunkX = playerChunkX + x, chunkZ = playerChunkZ + z;
                        chunkList.add(player.getWorld().getChunkAt(chunkX, chunkZ));
                    }
                }
                break;
            case WEST:
                for (int x = -2; x <= 2; x++) {
                    for (int z = 4; z >= -4; z--){
                        int chunkX = playerChunkX + x, chunkZ = playerChunkZ + z;
                        chunkList.add(player.getWorld().getChunkAt(chunkX, chunkZ));
                    }
                }
                break;
        }
        return chunkList;
    }
}
