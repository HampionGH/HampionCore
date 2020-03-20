package com.craftbukkit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;

public class Entities extends JavaPlugin {

    @Override
    public void onEnable() {

        Entity zombie = Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 0, 0, 0),
                EntityType.ZOMBIE);
        zombie.setGlowing(true);

        Zombie zombie1 = (Zombie) zombie;
        Creeper creeper = (Creeper) zombie;

    }
}
