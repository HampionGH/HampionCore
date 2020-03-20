package com.craftbukkit.Listeners;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;

public class GodListener implements Listener, CommandExecutor {

    private Main main;

    public GodListener(Main main) {
        this.main = main;
    }

    ArrayList<Player> enabled = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            System.out.println("[HampionCore] You cannot use this command in console!");
        }


        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (!player.hasPermission("hampioncore.god")) {
                player.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED + "" +
                        ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");

            } else if (player.hasPermission("hampioncore.god") && (enabled.contains(player))) { // IS ENABLED
                player.sendMessage(ChatColor.BLUE + "You have disabled god mode!");
                player.setInvulnerable(false);
                player.setFoodLevel(20);
                player.setHealth(20);
                enabled.remove(player);

            } else if (player.hasPermission("hampioncore.god")) { // IS DISABLED
                player.setInvulnerable(true);
                player.setFoodLevel(20);
                player.setHealth(20);
                player.sendMessage(ChatColor.BLUE + "You have enabled god mode!");
                enabled.add(player);
            }
        }
        return false;
    }

    @EventHandler
    public void onHungerDeplete(FoodLevelChangeEvent e) {

    }


}
