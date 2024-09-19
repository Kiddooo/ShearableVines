package dev.kiddo.shearableVines.events;

import com.jeff_media.customblockdata.CustomBlockData;
import dev.kiddo.shearableVines.ShearableVines;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.persistence.PersistentDataType;


import static dev.kiddo.shearableVines.ShearableVines.SHEARED_KEY;
//import static dev.kiddo.shearableVines.ShearableVines.pluginLogger;

public class VineGrowthHandler implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.VINE) {
            CustomBlockData customBlockData = new CustomBlockData(block, ShearableVines.plugin);

            if (customBlockData.has(SHEARED_KEY, PersistentDataType.BOOLEAN)) {
//                pluginLogger.info("Removed sheared vine at " + block.getLocation());
                customBlockData.remove(SHEARED_KEY);
            }
        }
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        Block sourceBlock = event.getSource();
        Block targetBlock = event.getBlock();

        if (sourceBlock.getType() == Material.VINE && !isSheared(sourceBlock)) {
//            pluginLogger.info("Regular vine growth detected. Source: " + sourceBlock.getLocation());

            // Allow regular vine growth
            return;
        }

        if (sourceBlock.getType() == Material.VINE && isSheared(sourceBlock)) {
//            pluginLogger.info("Sheared vine spread attempt detected. Source: " + sourceBlock.getLocation() + ", Target: " + targetBlock.getLocation());

            // Cancel the spread event
            event.setCancelled(true);

            // Mark the target block as sheared to prevent future spreads
            CustomBlockData customBlockData = new CustomBlockData(targetBlock, ShearableVines.plugin);
            customBlockData.set(SHEARED_KEY, PersistentDataType.BOOLEAN, true);
            targetBlock.getState().update(true);

//            pluginLogger.info("Target block marked as sheared: " + targetBlock.getLocation());
        }
    }

    private boolean isSheared(Block block) {
        CustomBlockData customBlockData = new CustomBlockData(block, ShearableVines.plugin);
        return customBlockData.has(SHEARED_KEY, PersistentDataType.BOOLEAN);
    }
}
