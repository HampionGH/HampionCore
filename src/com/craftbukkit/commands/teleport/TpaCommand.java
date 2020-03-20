package com.craftbukkit.commands.teleport;

import com.craftbukkit.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jcp.xml.dsig.internal.dom.Utils;

import java.awt.*;
import java.util.ArrayList;

public class TpaCommand implements CommandExecutor {

    private Main main;

    public TpaCommand(Main main) {
        this.main = main;
    }
    // number <number>
    public static ArrayList<String> tp = new ArrayList<String>();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                Player p2 = Bukkit.getPlayer(args[0]);
                if (p2 != null && p2.isOnline()) {

                    if (!p.getName().equals(p2.getName())) {
                        if (!main.getTeleportrequest().containsKey(p)) {
                            main.getTeleportrequest().put(p2, p);

                            p.sendMessage(ChatColor.GRAY + "Sent a teleport request to " + ChatColor.AQUA + "" + p2.getName());
                            p2.sendMessage(ChatColor.AQUA + "" + p.getName() + ChatColor.GRAY + " has requested to teleport to you!\nUse "
                                    + ChatColor.AQUA + "/tpaccept " + ChatColor.GRAY + "to accept it!" );
                            Bukkit.getWorld("world").playSound(p.getPlayer().getLocation(),
                                    Sound.ENTITY_VILLAGER_YES, 1.0F, 1.0F);

                            tp.add(p.getName() + ">" + p2.getName());
                            Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), new Runnable() {
                                @Override
                                public void run() {
                                    for (int a = 0; a < tp.size(); a++) {
                                        if (tp.get(a).equals(p.getName() + ">" + ))
                                    }
                                }
                            });

                        } else {
                            p.sendMessage(ChatColor.RED + "You have already requested to teleport to that player..");
                            Bukkit.getWorld("world").playSound(p.getPlayer().getLocation(),
                                    Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "I'm sorry but that player is not online..");
                        Bukkit.getWorld("world").playSound(p.getPlayer().getLocation(),
                                Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Error: use /tpa <player>");
                    Bukkit.getWorld("world").playSound(p.getPlayer().getLocation(),
                            Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                }
            } else {
                p.sendMessage(ChatColor.RED + "Error: use /tpa <player>");
                Bukkit.getWorld("world").playSound(p.getPlayer().getLocation(),
                        Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }
        }
        return true;
    }
}


