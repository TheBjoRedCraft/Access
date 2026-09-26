package de.thebjoredcraft.access;

import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WorldAccessListener implements Listener {
  private final String BYPASS_PERMISSION = "access.bypass";

    @EventHandler
    public void onWorldChange(PlayerTeleportEvent event){
        Player target = event.getPlayer();

        if (!target.hasPermission("access.worlds." + event.getTo().getWorld().getName()) && !target.hasPermission(BYPASS_PERMISSION)){
            event.setCancelled(true);
            AccessManager.denied(target, event.getTo().getWorld());
        } else {
            AccessManager.allowed(target, event.getTo().getWorld());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player target = event.getPlayer();
        int worldCount = Bukkit.getWorlds().size();
        int currentCount = 0;

        if (target.hasPermission("access.worlds." + target.getWorld().getName()) || target.hasPermission(BYPASS_PERMISSION)){
            AccessManager.allowed(target, target.getWorld());
        } else {
            for(World targetWorld : Bukkit.getWorlds()){
                currentCount ++;
                if(target.hasPermission("access.worlds." + targetWorld.getName()) || target.hasPermission(BYPASS_PERMISSION)){
                    target.teleport(targetWorld.getSpawnLocation());
                    AccessManager.allowed(target, targetWorld);
                    return;
                }
                if(currentCount == worldCount){
                  Bukkit.getConsoleSender().sendRichMessage("<" + NamedTextColor.RED.asHexString() + ">[Access] Denied join for player " + target.getName() + " because no accessible world was found.");
                  target.kick(MiniMessage.miniMessage().deserialize("<red>Es wurde keine Welt gefunden, zu der du Zugriff hast!"));
                  currentCount = 0;
                }
            }
        }
    }
}
