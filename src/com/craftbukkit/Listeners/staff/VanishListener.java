package com.craftbukkit.Listeners.staff;

import com.craftbukkit.Main;
import com.craftbukkit.commands.staff.VanishCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class VanishListener implements Listener {

    Main plugin;
    public VanishListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (int i = 0; i < plugin.vanished.size(); i++) {
            player.hidePlayer(plugin, plugin.vanished.get(i));
        } if (plugin.vanished.contains(e.getPlayer())) {
            player.sendMessage(ChatColor.AQUA + "You disconnected so vanish was removed!");
        }
    }

    @EventHandler
    public void PlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        plugin.vanished.remove(player);
    }
}
