package de.thebjoredcraft.access;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AccessManager {
    public static void denied(Player player) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Access] Denied world access for " + player.getName());
        player.sendActionBar(MiniMessage.miniMessage().deserialize("<color:#ff0004><b>Access denied</b></color>"));
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
    }

    public static void allowed(Player player) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Access] Allowed world access for " + player.getName());
        player.sendActionBar(MiniMessage.miniMessage().deserialize("<color:#95ff00><b>Access allowed....</b></color>"));
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 10, 1);
    }
}
