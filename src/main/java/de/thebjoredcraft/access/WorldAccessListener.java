package de.thebjoredcraft.access;

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
    @EventHandler
    public void onWorldChange(PlayerTeleportEvent event){
        Player target = event.getPlayer();
        if(!target.hasPermission("access.worlds." + event.getTo().getWorld().getName())){
            event.setCancelled(true);
            AccessManager.denied(target);
        }else{
            AccessManager.allowed(target);
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player target = event.getPlayer();
        int worldCount = Bukkit.getWorlds().size();
        int currentCount = 0;

        if(target.hasPermission("access.worlds." + target.getWorld().getName())){
            AccessManager.allowed(target);
        }else{
            for(World targetWorld : Bukkit.getWorlds()){
                currentCount ++;
                if(target.hasPermission("access.worlds." + targetWorld.getName())){
                    target.teleport(targetWorld.getSpawnLocation());
                    AccessManager.allowed(target);
                    return;
                }
                if(currentCount == worldCount){
                    target.kick(MiniMessage.miniMessage().deserialize("<bold>Es wurde keine Welt gefunden, zu der du Zugriff hast!"));
                    currentCount = 0;
                }
            }
        }
    }
}
