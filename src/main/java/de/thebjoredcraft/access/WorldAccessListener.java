package de.thebjoredcraft.access;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
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
}
