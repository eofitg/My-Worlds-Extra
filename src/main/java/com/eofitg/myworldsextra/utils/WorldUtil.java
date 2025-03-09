package com.eofitg.myworldsextra.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class WorldUtil {

    public static List<String> getAllWorlds() {
        List<String> worlds = new ArrayList<>();

        // Get the console sender
        ConsoleCommandSender console = Bukkit.getConsoleSender();

        // Prepare to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Execute the "mw list" command
        Bukkit.getServer().dispatchCommand(console, "mw list");

        // Retrieve the captured output
        String output = outputStream.toString();
        String[] lines = output.split("\n");

        // Parse each line of the output to extract world names
        for (String line : lines) {
            // Trim leading and trailing spaces
            line = line.trim();

            // Look for worlds that are either loaded or unloaded
            if (line.contains("[Unloaded]") || line.contains("[Loaded]")) {
                String worldName = line.split(" ")[0]; // Extract the world name
                worlds.add(worldName); // Add to the list of world names
            }
        }

        return worlds;
    }

    public static boolean notDefaultWorld(String worldName) {
        return !worldName.equals("world") && !worldName.equals("world_nether") && !worldName.equals("world_the_end");
    }

}
