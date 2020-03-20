/*package com.craftbukkit.commands.teleport;

import com.craftbukkit.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    private Main main;

    public TeleportCommand(Main main) {
        this.main = main;
    }
    // number <number>

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if (sender instanceof ConsoleCommandSender) {
            System.out.println("[HampionCore] You cannot use this command in console!");
        }

        if ((p.hasPermission("hampioncore.tpa") && (cmd.getName().equalsIgnoreCase("tpa")))) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Dude are you stupid? Specify a player..");
                return true;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "I'm sorry this retard isn't online right now.");
                return true;

            } else if (main.cooldown.containsKey(p) && main.cooldown.get(p) > System.currentTimeMillis()) {
                long longRemaining = main.cooldown.get(p) - System.currentTimeMillis();
                int intRemaining = (int) (longRemaining / 1000);

                p.sendMessage(ChatColor.GRAY + "Please wait, " + ChatColor.RED + intRemaining + ChatColor.GRAY
                        + " seconds before teleporting again.");

            } else if (p.hasPermission("hampioncore.bypass")) {
                main.teleportrequest(target, p);
                main.teleportrequest(p, target);
                main.teleportrequest.containsKey(target);
                p.sendMessage(ChatColor.GRAY + "Sent a teleport request to " + ChatColor.AQUA + "" + target.getName());
                target.sendMessage(ChatColor.AQUA + "" + p.getName() + ChatColor.GRAY + " has requested to teleport to you!\nUse "
                        + ChatColor.AQUA + "/tpaccept " + ChatColor.GRAY + "to accept it!" );
            } else if (!p.hasPermission("hampioncore.bypass")) {
                main.teleportrequest(target, p);
                main.teleportrequest(p, target);
                p.sendMessage(ChatColor.GRAY + "Sent a teleport request to " + ChatColor.AQUA + "" + target.getName());
                target.sendMessage(ChatColor.AQUA + "" + p.getName() + ChatColor.GRAY + " has requested to teleport to you!\nUse "
                        + ChatColor.AQUA + "/tpaccept " + ChatColor.GRAY + "to accept it!" );
                main.cooldown.put(p, System.currentTimeMillis() + (60 * 1000));
            }
            return true;
        } else {
            p.sendMessage(ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED + "" +
                    ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");
        }

        return true;
    }

}*/