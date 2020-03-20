package com.craftbukkit.commands.teleport;

import com.craftbukkit.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TpaDenyCommand implements CommandExecutor {

    private Main main;
    public int times = 0;
    int schedular = 1;

    public TpaDenyCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(main.getTeleportrequest().containsKey(p)) {
                Player p1 = main.getTeleportrequest().get(p);
                if(p1 != null && p1.isOnline()) {
                    times = 0;
                    schedular = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main.class), new Runnable() {
                        @Override
                        public void run() {
                            int timer = 5 - times;
                            if (timer > 0) {
                                p.sendMessage(ChatColor.AQUA + "Denied " + ChatColor.GRAY + " teleport request from "
                                        + ChatColor.AQUA + p1.getName());
                                p1.sendMessage(ChatColor.GRAY + "You're teleport request to " + ChatColor.AQUA +
                                        p.getName() + ChatColor.GRAY + " was denied!");
                            }
                            if (times == 5) {
                                main.getTeleportrequest().remove(p);
                                Bukkit.getServer().getScheduler().cancelTask(schedular);
                            } else {
                                Bukkit.getServer().getScheduler().cancelTask(schedular);
                            }
                        }
                    }, 0, 20);

                    p1.teleport(p.getLocation());
                    main.getTeleportrequest().remove(p);
                } else {
                    p.sendMessage(ChatColor.RED + "That player is no longer online.");
                    Bukkit.getWorld("world").playSound(p.getPlayer().getLocation(),
                            Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);

                    main.getTeleportrequest().remove(p);
                }
            } else {
                p.sendMessage(ChatColor.RED + "You don't have any teleport requests!");
                Bukkit.getWorld("world").playSound(p.getPlayer().getLocation(),
                        Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }

        }
        return true;
    }
}
