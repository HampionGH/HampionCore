package com.craftbukkit.commands.punishment;

import com.craftbukkit.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.Console;

public class BanCommand implements CommandExecutor, Listener {

    private Main main;

    public BanCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("hampioncore.punish")) {

            int length = args.length;
            if (length == 0) {
                sender.sendMessage(ChatColor.GRAY + "You need to " + ChatColor.RED + "specify " +
                        ChatColor.GRAY + "a player..");
                return true;
            }
            if (length == 1) {
                sender.sendMessage(ChatColor.RED + "Specify a reason...");
                return true;
            }
            if (length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }

                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ChatColor.RED + "Could not find player " + ChatColor.GREEN + "" +
                                ChatColor.UNDERLINE + args[0] + ChatColor.RED + " lol?");
                        return true;
                    }
                    if (!(target.hasPermission("hampioncore.bypass"))) {
                        target.kickPlayer(ChatColor.RED + "You have been banned!\n" + ChatColor.GREEN + "For "
                                + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ": " + ChatColor.GREEN + message.toString());

                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "", null, null);
                        Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Player " + ChatColor.YELLOW +
                                target.getName() + ChatColor.GRAY + " has been banned from the server :(");

                    } else if (target.hasPermission("hampioncore.bypass")) {
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.UNDERLINE +
                                "You may NOT ban that player");
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED +
                    ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");

        }
        return true;
    }
}