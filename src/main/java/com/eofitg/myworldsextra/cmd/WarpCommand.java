package com.eofitg.myworldsextra.cmd;

import com.eofitg.myworldsextra.utils.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {

    private final Plugin plugin;

    public WarpCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Usage: /warp <world_name>");
            return false;
        }

        String newWorldName = args[0];
        List<String> worlds = WorldUtil.getAllWorlds();

        if (!worlds.contains(newWorldName)) {
            player.sendMessage("World " + newWorldName + " not found.");
            return false;
        }

        // Only load the new world if it's not a default Minecraft world
        if (WorldUtil.notDefaultWorld(newWorldName)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mw load " + newWorldName);
        }

        player.sendMessage("Loading the world named " + newWorldName + " ...");
        // Delay to ensure new world is loaded before teleporting player
        Bukkit.getScheduler().runTask(plugin, () -> {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                // Check if the world is loaded
                World newWorld = Bukkit.getWorld(newWorldName);
                if (newWorld != null) {
                    // After the world is loaded, teleport the player to the new world
                    player.chat("/tpp " + newWorldName);
                } else {
                    player.sendMessage("Failed to load the world or world is still loading.");
                }
            }, 60L); // Wait for the world to load (3 seconds delay)
        });

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            // Get all the worlds and suggest their names
            for (String worldName : WorldUtil.getAllWorlds()) {
                // Only suggest worlds that match the typed argument
                if (worldName.startsWith(args[0])) {
                    suggestions.add(worldName);
                }
            }
        }

        return suggestions;
    }

}
