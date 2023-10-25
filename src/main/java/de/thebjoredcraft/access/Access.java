package de.thebjoredcraft.access;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Access extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new WorldAccessListener(), this);
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
