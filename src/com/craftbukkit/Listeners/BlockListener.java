package com.craftbukkit.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        if (e.getBlockPlaced().getType().equals(Material.BEDROCK) && (!(e.getPlayer().hasPermission
                ("hampioncore.bypass")))) {
            e.getBlockPlaced().setType(Material.SPONGE);
        }
    }
}
