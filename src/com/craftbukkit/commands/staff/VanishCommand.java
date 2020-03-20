package com.craftbukkit.commands.staff;

import com.craftbukkit.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor {

    Main plugin;

    public VanishCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {

        if ((sender instanceof Player) && ((sender.hasPermission("hampioncore.vanish")))) {
            Player player = (Player) sender;
            if (plugin.vanished.contains(player)) {
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.showPlayer(plugin, player);
                }
                plugin.vanished.remove(player);
                player.sendMessage(ChatColor.AQUA + "You are no longer vanished!");
            } else if (!plugin.vanished.contains(player)) {
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.hidePlayer(plugin, player);
                }
                plugin.vanished.add(player);
                player.sendMessage(ChatColor.AQUA + "You are now vanished!");
            }
        } else if ((sender instanceof Player) && (!(sender.hasPermission("hampioncore.vanish")))) {
            sender.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED +
                    ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");
        } else if (!(sender instanceof Player)) {
            System.out.println("You cannot use this command in console!");
        }
        return false;
    }
}
