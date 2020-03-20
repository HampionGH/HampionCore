package com.craftbukkit.Listeners.staff;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BanListener implements Listener {

    private Main main;

    public BanListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (player.isBanned()) {
            player.kickPlayer(ChatColor.RED + "You have been banned!\n" + ChatColor.GREEN + "Please, contact" +
                    "a member of staff.");
        }

    }

}
