package com.craftbukkit.projectiles;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MachineGun implements CommandExecutor {

    public Main main;

    public MachineGun(Main main) {this.main = main;}


    public ItemStack gun = new ItemStack(Material.STONE_HOE, 1);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
        if (sender instanceof ConsoleCommandSender) {
            System.out.println("[HampionCore] You cannot use this command in console!");
        }

        if(sender instanceof Player) {
            List<String> lore = new ArrayList <String>();
            Player player = (Player) sender;

            if (player.hasPermission("hampioncore.bypass")) {
                // What the player is getting
                // Item Info + Transfer
                ItemMeta gunmeta = gun.getItemMeta();
                gunmeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD +
                        "" + ChatColor.UNDERLINE + "USP-S");
                lore.add(ChatColor.GREEN + "Small but deadly");
                gunmeta.setLore(lore);
                gun.setItemMeta(gunmeta);
                player.getInventory().addItem(gun);
                player.sendMessage(ChatColor.RED + "Enjoy your gun, don't kill anyone.");

            } else if (main.cooldown.containsKey(player) && main.cooldown.get(player) > System.currentTimeMillis()) {
                long longRemaining = main.cooldown.get(player) - System.currentTimeMillis();
                int intRemaining = (int) (longRemaining / 10000);

                player.sendMessage(ChatColor.GRAY + "You just got one... please wait " + ChatColor.RED +
                        intRemaining + ChatColor.GRAY + " before getting another.");
            } else {
                // What the player is getting
                // Item Info + Transfer
                ItemMeta gunmeta = gun.getItemMeta();
                gunmeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD +
                        "" + ChatColor.UNDERLINE + "USP-S");
                lore.add(ChatColor.GREEN + "Small but deadly");
                gunmeta.setLore(lore);
                gun.setItemMeta(gunmeta);
                player.getInventory().addItem(gun);
                player.sendMessage(ChatColor.RED + "Enjoy your gun, don't kill anyone.");
                // Placing Cooldown
                main.cooldown.put(player, System.currentTimeMillis() + (60 * 10000));
            }
        }
        return false;
    }
}
