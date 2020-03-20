package com.craftbukkit.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakSound implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {

        Bukkit.getWorld("world").playSound(e.getPlayer().getLocation(),
                Sound.BLOCK_SLIME_BLOCK_BREAK, 1.0F, 1.0F);

    }

}
