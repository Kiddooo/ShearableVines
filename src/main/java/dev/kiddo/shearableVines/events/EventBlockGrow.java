package dev.kiddo.shearableVines.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static dev.kiddo.shearableVines.ShearableVines.SHEARED_KEY;
import static dev.kiddo.shearableVines.ShearableVines.pluginLogger;

public class EventBlockGrow implements Listener {

    @EventHandler
    public void onVineGrow(BlockGrowEvent event) {
        Block block = event.getBlock();
        if (block.getType().equals(Material.VINE)) {
            PersistentDataContainer persistentDataType = block.getState().getChunk().getPersistentDataContainer();
            pluginLogger.info(persistentDataType.getKeys() + " " + persistentDataType.get(SHEARED_KEY, PersistentDataType.BOOLEAN));
            if (persistentDataType.has(SHEARED_KEY, PersistentDataType.BOOLEAN)) {
                event.setCancelled(true);
                pluginLogger.info("Prevented block growth at " + block.getLocation());
            }
        }
    }
}
