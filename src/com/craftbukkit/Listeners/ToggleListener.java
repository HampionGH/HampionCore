package com.craftbukkit.Listeners;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;

public class ToggleListener implements Listener, CommandExecutor {

    private Main main;

    public ToggleListener(Main main) {
        this.main = main;
    }

    ArrayList<Player> enabled = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            System.out.println("[HampionCore] You cannot use this command in console!");
        }

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (!player.hasPermission("hampioncore.fly")) {
                player.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED + "" +
                        ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");

            } else if (player.getGameMode().equals(GameMode.CREATIVE)) {
                player.sendMessage(ChatColor.RED + "You already have fly enabled...");

            } else if (player.hasPermission("hampioncore.fly") && (enabled.contains(player) &&
                    player.getGameMode().equals(GameMode.SURVIVAL))) { // IS ENABLED
                player.sendMessage(ChatColor.BLUE + "You have disabled fly mode!");
                player.setAllowFlight(false);
                player.setFlying(false);
                enabled.remove(player);

            } else if (player.hasPermission("hampioncore.fly") && (player.getGameMode().equals(
                    GameMode.SURVIVAL))) { // IS DISABLED
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage(ChatColor.BLUE + "You have enabled fly mode!");
                enabled.add(player);
            }
        }
        return false;
    }

}
