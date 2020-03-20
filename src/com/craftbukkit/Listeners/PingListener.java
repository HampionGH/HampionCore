package com.craftbukkit.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class PingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {

        e.setMaxPlayers(69);
        e.setMotd(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "✸ " + ChatColor.LIGHT_PURPLE + ""
                + ChatColor.BOLD + "HampionCore Plugin Test Server" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + " ✸" + "\n" +
                ChatColor.AQUA + "Now supporting AMD maggots!");
        try {
            e.setServerIcon(Bukkit.loadServerIcon(new File("sever-icon.png")));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}