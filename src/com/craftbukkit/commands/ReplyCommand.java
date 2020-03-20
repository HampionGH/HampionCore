package com.craftbukkit.commands;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {

    private Main main;

    public ReplyCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length > 0) {
            if (main.getMessageManager().recentlyMessaged.containsKey(player)) {
                if (main.getMessageManager().recentlyMessaged.get(player) != null) {
                    Player target = main.getMessageManager().recentlyMessaged.get(player);

                    StringBuilder message = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }

                    target.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD +
                            player.getName() + ChatColor.GRAY
                            + " ＞ " + ChatColor.BOLD + "you: " + ChatColor.GRAY + message.toString());
                    player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "you " + ChatColor.GRAY
                            + "＞ " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + target.getName() +
                            ChatColor.GRAY + ChatColor.BOLD + ": " + message.toString());

                } else {
                    player.sendMessage(ChatColor.RED + "That player is no longer online.");
                }

            } else {
                player.sendMessage(ChatColor.RED + "You have not messaged anyone recently.");
            }
        } else {
            player.sendMessage("Invalid useage! Use /reply <message>");
        }

        return false;

    }

}