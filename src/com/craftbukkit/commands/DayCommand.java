package com.craftbukkit.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            System.out.println("[HampionCore] You cannot use this command in console!");
        }


        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (!player.hasPermission("hampioncore.time")) {
                player.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED + "" +
                        ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");

            } else if (player.hasPermission("hampioncore.time")) {
                player.getWorld().setTime(6000);
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD +
                        "Time set to 12:00pm");

            }

        }


        return false;
    }


}