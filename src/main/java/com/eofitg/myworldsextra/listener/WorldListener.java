package com.eofitg.myworldsextra.listener;

import com.eofitg.myworldsextra.utils.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldListener implements Listener {

    private final Plugin plugin;

    public WorldListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // MyWorlds-World Unload Listener
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        World oldWorld = event.getFrom();

        // Check if the old world is not a default Minecraft world and has no players
        if (WorldUtil.notDefaultWorld(oldWorld.getName()) && oldWorld.getPlayers().isEmpty()) {
            // Schedule world unloading after the player has left the world
            Bukkit.getScheduler().runTask(plugin, () -> {
                if (oldWorld.getPlayers().isEmpty()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mw unload " + oldWorld.getName());
                }
            });

            Bukkit.getConsoleSender().sendMessage("§a[MyWorldsExtra] World " + oldWorld.getName() + " has been successfully unloaded.");
        }
    }

}
