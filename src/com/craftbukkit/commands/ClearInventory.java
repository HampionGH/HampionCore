package com.craftbukkit.commands;

import com.craftbukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor {

    private Main main;

    public ClearInventory(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if(args.length == 0) {
            p.sendMessage(ChatColor.GRAY + "You typed too many command " +
                    ChatColor.RED + "arguments!");
        } else if(args.length > 0) {
            p.getServer().getPlayer(args[0]);
        } if (p == null) {
            return false;
        }
        p.getInventory().clear();
        p.updateInventory();
        p.sendMessage(ChatColor.GRAY + "Successfully cleared the inventory of " + ChatColor.AQUA +
                p);

        return false;
    }
}
