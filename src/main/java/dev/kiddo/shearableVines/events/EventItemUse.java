package dev.kiddo.shearableVines.events;

import com.jeff_media.customblockdata.CustomBlockData;
import dev.kiddo.shearableVines.ShearableVines;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import static dev.kiddo.shearableVines.ShearableVines.SHEARED_KEY;

public class EventItemUse implements Listener {


    @EventHandler
    public void onItemUseEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.getItem() != null && event.getItem().getType().equals(Material.SHEARS) && event.getAction().isRightClick()) {
            if (block != null && block.getType().equals(Material.VINE)) {
                CustomBlockData customBlockData = new CustomBlockData(event.getClickedBlock(), ShearableVines.plugin);

                if (!customBlockData.has(SHEARED_KEY, PersistentDataType.BOOLEAN)) {
                    customBlockData.set(SHEARED_KEY, PersistentDataType.BOOLEAN, true);
                    player.sendMessage("You have sheared the vine. It will no longer grow.");
                    event.setCancelled(true);
                } else {
                    player.sendMessage("This vine has already been sheared");
                }
            }
        }
    }
}
