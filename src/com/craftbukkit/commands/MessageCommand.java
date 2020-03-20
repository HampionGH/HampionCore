package com.craftbukkit.commands;

import com.craftbukkit.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    private Main main;

    public MessageCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length >= 2) {
            if(Bukkit.getPlayerExact(args[0]) != null) {
                Player target = Bukkit.getPlayerExact(args[0]);

                StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    message.append(args[i]).append(" ");
                }

                target.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD +
                        player.getName() + ChatColor.GRAY
                        + " ＞ " + ChatColor.BOLD + "you: " + ChatColor.GRAY + message.toString());
                player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "you " + ChatColor.GRAY
                        + "＞ " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + target.getName() +
                        ChatColor.GRAY + ChatColor.BOLD + ": " + message.toString());

                main.getMessageManager().recentlyMessaged.put(player, target);
                main.getMessageManager().recentlyMessaged.put(target, player);

            } else {
                player.sendMessage(ChatColor.RED + "Player not found!");
            }

        } else {
            player.sendMessage(ChatColor.RED + "Invalid usage! Use /message <player> <message>");
        }


        return false;

    }

}
