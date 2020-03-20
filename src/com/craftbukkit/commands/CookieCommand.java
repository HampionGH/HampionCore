package com.craftbukkit.commands;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class CookieCommand implements CommandExecutor {
    private Main main;

    public CookieCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {

        if (sender instanceof ConsoleCommandSender) {
            System.out.println("[HampionCore] You cannot use this command in console!");
        }

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("hampioncore.bypass")) {
                // What the player is getting
                ItemStack cookie = new ItemStack(Material.COOKIE, 1);
                // Item Info + Transfer
                ItemMeta cookiemeta = cookie.getItemMeta();
                cookiemeta.setDisplayName(ChatColor.translateAlternateColorCodes
                        ('&', "&6&lHampion Cookies"));
                player.getInventory().addItem(cookie);
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Yum yum cookies for you!");

            } else if (main.cooldown.containsKey(player) && main.cooldown.get(player) > System.currentTimeMillis()) {
                long longRemaining = main.cooldown.get(player) - System.currentTimeMillis();
                int intRemaining = (int) (longRemaining / 1000);

                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Mate, diabetes is real wait " +
                        intRemaining + ChatColor.RED + ChatColor.BOLD + " seconds fatty.");

            } else {
                // What the player is getting
                ItemStack cookie = new ItemStack(Material.COOKIE, 1);
                // Item Info + Transfer
                ItemMeta cookiemeta = cookie.getItemMeta();
                cookiemeta.setDisplayName(ChatColor.translateAlternateColorCodes
                        ('&', "&6&lHampion Cookies"));
                player.getInventory().addItem(cookie);
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Yum yum cookies for you!");
                // Placing Cooldown
                main.cooldown.put(player, System.currentTimeMillis() + (60 * 1000));

            }
        }
        return false;
    }

}
