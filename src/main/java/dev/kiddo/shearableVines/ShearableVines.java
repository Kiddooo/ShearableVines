package dev.kiddo.shearableVines;

import dev.kiddo.shearableVines.events.EventBlockGrow;
import dev.kiddo.shearableVines.events.EventItemUse;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ShearableVines extends JavaPlugin {

    public static NamespacedKey SHEARED_KEY;
    public static ShearableVines plugin;
    public static Logger pluginLogger;

    @Override
    public void onEnable() {
        plugin = this;
        pluginLogger = getLogger();
        SHEARED_KEY = new NamespacedKey(this, "is_sheared");

        getServer().getPluginManager().registerEvents(new EventItemUse(), this);
        getServer().getPluginManager().registerEvents(new EventBlockGrow(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
