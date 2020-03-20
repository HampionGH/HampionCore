package com.craftbukkit.managers;

import com.craftbukkit.Main;
import com.craftbukkit.commands.MessageCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageManager {

    private Main main;
    public MessageManager(Main main) {
        this.main = main;
    }

    public HashMap<Player, Player> recentlyMessaged = new HashMap<>();

}
