package com.craftbukkit;

import com.craftbukkit.Listeners.*;
import com.craftbukkit.Listeners.staff.BanListener;
import com.craftbukkit.Listeners.staff.VanishListener;
import com.craftbukkit.commands.*;
import com.craftbukkit.commands.punishment.BanCommand;
import com.craftbukkit.commands.punishment.KickCommand;
import com.craftbukkit.commands.punishment.PardonCommand;
import com.craftbukkit.commands.staff.VanishCommand;
import com.craftbukkit.commands.teleport.TpaAcceptCommand;
import com.craftbukkit.commands.teleport.TpaCommand;
import com.craftbukkit.managers.MessageManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    // Array Lists
    public List<Player> vanished = new ArrayList<>();

    private MessageManager manager;

    @Override
    public void onEnable() {

        Logger Logger = Bukkit.getLogger();
        ConsoleCommandSender clogger = this.getServer().getConsoleSender();
        // must type .sendMessage(color + "Message") after i

        clogger.sendMessage(ChatColor.AQUA + "[HampionCore]: Loading core elements..");
        clogger.sendMessage(ChatColor.AQUA + "[HampionCore]: Plugin has been loaded successfully.");

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("cookie").setExecutor(new CookieCommand(this));
        getCommand("creative").setExecutor(new CreativeCommand());
        getCommand("survival").setExecutor(new SurvivalCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("night").setExecutor(new NightCommand());
        getCommand("fly").setExecutor(new ToggleListener(this));
        getCommand("hg").setExecutor(new HologramListener(this));
        getCommand("god").setExecutor(new GodListener(this));
        getCommand("kick").setExecutor(new KickCommand(this));
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
        getCommand("unban").setExecutor(new PardonCommand(this));
        getCommand("menu").setExecutor(new MenuCommand(this));
        getCommand("tpa").setExecutor(new TpaCommand(this));
        getCommand("tpaccept").setExecutor(new TpaAcceptCommand(this));
        publicStand = null;
        spawnStand(new Location(Bukkit.getWorld("World"), 464.047, 79.6, -505.014 ));

        Bukkit.getPluginManager().registerEvents(new PingListener(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new VanishListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SneakSound(),this);
        Bukkit.getPluginManager().registerEvents(new BanListener(this), this);
        manager = new MessageManager(this);
    }

    public MessageManager getMessageManager()
    { return manager; }

    public ArmorStand publicStand;

    // UI's
    public void applyElytraUI(Player player) {

        // Inventory Info
        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Elytra menu!");
        // Item Lores
        List<String> enableLore = new ArrayList<>();
        enableLore.add(ChatColor.GRAY + "Click me to");
        enableLore.add(ChatColor.GRAY + "fly with the winds");

        List<String> disableLore = new ArrayList<>();
        enableLore.add(ChatColor.GRAY + "Click me to");
        enableLore.add(ChatColor.GRAY + "lose your damn wings");

        // Item Stacks
        ItemStack toggle;
        ItemMeta toggleMeta;
        if (player.getInventory().getChestplate() != null &&
                player.getInventory().getChestplate().equals(Material.ELYTRA)) {
            toggle = new ItemStack(Material.REDSTONE_BLOCK);

            toggleMeta = toggle.getItemMeta();
            toggleMeta.setDisplayName(ChatColor.RED + "Disable Elytra!");
            toggleMeta.setLore(enableLore);

        } else {
            toggle = new ItemStack(Material.EMERALD_BLOCK);

            toggleMeta = toggle.getItemMeta();
            toggleMeta.setDisplayName(ChatColor.GREEN + "Enable Elytra!");
            toggleMeta.setLore(enableLore);
        }
        toggle.setItemMeta(toggleMeta);

        // Item Settings

        gui.setItem(4, toggle);

        // Final

        player.openInventory(gui);

    }


    // Cooldown Controller

    public HashMap<Player, Long> cooldown = new HashMap<>();
    private HashMap<Player, Player> teleportrequest = new HashMap<Player, Player>();


    @Override
    public void onDisable() {
        Logger Logger = Bukkit.getLogger();
        ConsoleCommandSender clogger = this.getServer().getConsoleSender();
        // must type .sendMessage(color + "Message") after i
        clogger.sendMessage(ChatColor.RED + "[HampionCore]: Packing up awesome features..");
        clogger.sendMessage(ChatColor.RED + "[HampionCore]: Plugin has been unloaded successfully");

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if(!player.hasPermission("hampioncore.move")) {
            e.setCancelled(true);

        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setResourcePack("http://download2266.mediafire.com/k9ibzvh1vcsg/yac3369niuqmjbe/%C2%A7bDynamic+Duo+%C2%A78%5B%C2%A7f128x%C2%A78%5D.zip");

        player.sendMessage(ChatColor.GRAY + "Welcome " + ChatColor.BLUE + player.getName() + ChatColor.GRAY +
                " to Hampions test server!");
    }

    @EventHandler
    public void onThrow(PlayerEggThrowEvent event) {

        Player player = event.getPlayer();

        player.sendMessage(ChatColor.YELLOW + "sPlAt!");

    }

    @EventHandler
    public void onSpeak(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if(!player.hasPermission("hampioncore.chat")) {
            player.sendMessage(ChatColor.DARK_RED + "Such a tiny brain you have ;)");
            event.setCancelled(true);

        }

    }

    private void spawnStand(Location location) {

        ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        stand.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD + "The Master Sword..");
        stand.setCustomNameVisible(true);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setVisible(false);

        stand.setBasePlate(false);

        stand.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));

        stand.setRightArmPose(new EulerAngle(Math.toRadians(275), Math.toRadians(0), Math.toRadians(0)));

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equals("welcome")) {
            String word = this.getConfig().getString("Word");
            int number = this.getConfig().getInt("Number");

            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GRAY + "Welcome to " + ChatColor.GREEN + word + ChatColor.GRAY +
                        " We have been open since " + ChatColor.GREEN + number);

            } else if (sender instanceof ConsoleCommandSender){
                System.out.println("You can't run this command in the console...");

            }

        }

        return false;
    }

    public HashMap<Player, Player> getTeleportrequest() {
        return teleportrequest;
    }
}
