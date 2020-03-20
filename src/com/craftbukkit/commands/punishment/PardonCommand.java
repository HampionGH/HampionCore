package com.craftbukkit.commands.punishment;

import com.craftbukkit.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PardonCommand implements CommandExecutor {

    private Main main;

    public PardonCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("hampioncore.unban")) {

            Player player = (Player) sender;
            int length = args.length;

            if (length != 1) {
                player.sendMessage(ChatColor.RED + "Use /unban <player>");
                return true;
            }

            BanList target = Bukkit.getBanList(BanList.Type.NAME);
            if (target.isBanned(args[0])) {
                target.pardon(args[0]);
                player.sendMessage(ChatColor.GRAY + "Successfully unbanned " +
                        ChatColor.GREEN + target);
            } else if (!target.isBanned(args[0])) {
                sender.sendMessage(ChatColor.GRAY + "I'm sorry, we " + ChatColor.RED + "" +
                        ChatColor.UNDERLINE + "couldn't" + ChatColor.GRAY + " find that player..");
            }
        } else {
            sender.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED + "" +
                    ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");
        }
        return true;
    }

}
