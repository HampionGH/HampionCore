package com.craftbukkit.Listeners;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HologramListener implements CommandExecutor {

    private Main main;

    public HologramListener(Main main) {
        this.main = main;
    }


    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equals("hg")) {

            if (sender instanceof ConsoleCommandSender) {
                System.out.println("[HampionCore] You cannot use this command in console!");
            }


            if (sender instanceof Player) {

                Player player = (Player) sender;

                if (!player.hasPermission("hampioncore.hologram")) {
                    player.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED + "" +
                            ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");

                } else if (player.hasPermission("hampioncore.hologram")) {
                    ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 1.5, 0),
                            EntityType.ARMOR_STAND);
                    stand.setVisible(false);
                    stand.setGravity(false);
                    stand.setInvulnerable(false);

                    stand.setCustomNameVisible(true);
                    stand.setCustomName(ChatColor.GRAY + "Created by " + ChatColor.GREEN + player.getName());


                }
            }
        }
        return false;
    }

}
