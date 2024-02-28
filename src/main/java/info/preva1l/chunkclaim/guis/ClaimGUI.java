package info.preva1l.chunkclaim.guis;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import info.preva1l.chunkclaim.ChunkClaim;
import info.preva1l.chunkclaim.config.Config;
import info.preva1l.chunkclaim.data.impl.ChunkHelper;
import info.preva1l.chunkclaim.guis.util.GuiUtil;
import info.preva1l.chunkclaim.utils.Sound;
import info.preva1l.chunkclaim.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;


public class ClaimGUI extends FastInv {
    private final Player player;
    private final ChunkClaim plugin;
    public ClaimGUI(Player player) {
        super(54);
        this.player = player;
        this.plugin = ChunkClaim.i();
        placeChunkItems();
    }

    private void placeChunkItems() {
        int index = 0;
        for (Chunk chunk : GuiUtil.getNearByChunksRelativeToPlayerAndMenu(player)) {
            if (index == 45) return;
            boolean isClaimable = ChunkHelper.isClaimable(chunk);

            setItem(index, new ItemBuilder(getClaimItem(index, chunk))
                            .name(Text.message((isClaimable ? "&aUnclaimed" : "&cClaimed") + " &8(" + ChunkHelper.getReadableCoords(chunk) + ")"))
                            .addLore(Text.message(isClaimable ? "&aUnclaimed" : "&cClaimed"))
                            .build(),
                    e -> {
                        if (!isClaimable) {
                            Sound.fail(player);
                        } else {
                            plugin.getClaimStorage().createClaim(Config.SERVER_NAME.toString(), chunk).join();
                            placeChunkItems();
                            Sound.success(player);
                        }
                    });
            ++index;
        }
    }

    private ItemStack getClaimItem(int index, Chunk chunk) {
        if (index == 23) {
            return new ItemBuilder(Material.NETHER_STAR).build();
        }
        if (!ChunkHelper.isClaimable(chunk)) {
            return skullOwner(new ItemStack(Material.PLAYER_HEAD), Bukkit.getOfflinePlayer(plugin.getClaimStorage().getClaim(Config.SERVER_NAME.toString(), chunk).join().owner()));
        }
        return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).build();
    }

    private ItemStack skullOwner(ItemStack item, OfflinePlayer owner) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(owner);
        item.setItemMeta(meta);
        return item;
    }
}
