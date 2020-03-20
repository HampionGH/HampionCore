/*package com.craftbukkit.commands.punishment;

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
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.Console;

public class MuteCommand implements CommandExecutor, Listener {

    private Main main;
    public MuteCommand(Main main) {
        this.main = main;
    }

    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, AsyncPlayerChatEvent e, String label, String[] args) {
        if (sender.hasPermission("hampioncore.punish")) {

            int length = args.length;
            if (length == 0) {
                sender.sendMessage(ChatColor.GRAY + "You need to " + ChatColor.RED + "specify " +
                        ChatColor.GRAY + "a player..");
                return true;
            }
            if (length == 1) {
                sender.sendMessage(ChatColor.GRAY + "You need to " + ChatColor.RED + "specify " +
                        ChatColor.GRAY + "a reason..");
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Could not find player " + ChatColor.GREEN + "" +
                        ChatColor.UNDERLINE + args[0] + ChatColor.RED + " lol?");
                return true;
            }
            if (!(target.hasPermission("hampioncore.bypass"))) {
                Player player = e.getPlayer();
                e.setCancelled(true);


                }
                e.setCancelled(true);
                target.kickPlayer(ChatColor.RED + "You have been muted.. BEHAVE!");

            } else if (player.hasPermission("hampioncore.bypass")) {
                sender.sendMessage(ChatColor.RED + "" + ChatColor.UNDERLINE +
                        "You may NOT ban that player");
            }
        }
        return false;

    }
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}*/