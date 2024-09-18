package dev.kiddo.shearableVines.events;

import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static dev.kiddo.shearableVines.ShearableVines.SHEARED_KEY;

public class EventItemUse implements Listener {


    @EventHandler
    public void onItemUseEvent(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType().equals(Material.SHEARS)) {
            Block block = event.getClickedBlock();
            if (block != null && block.getType().equals(Material.VINE)) {
                PersistentDataContainer persistentDataContainer = block.getState().getWorld().getPersistentDataContainer();
                persistentDataContainer.set(SHEARED_KEY, PersistentDataType.BOOLEAN, true);

                event.getPlayer().sendMessage("You have sheared the vine. It will no longer grow.");
                event.setCancelled(true);
            }
        }
    }
}
