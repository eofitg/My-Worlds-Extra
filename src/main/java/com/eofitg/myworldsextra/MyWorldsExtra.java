package com.eofitg.myworldsextra;

import com.eofitg.myworldsextra.cmd.WarpCommand;
import com.eofitg.myworldsextra.listener.WorldListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyWorldsExtra extends JavaPlugin {

    @Override
    public void onEnable() {

        // Register the /warp command and its tab completer
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("warp").setTabCompleter(new WarpCommand(this));

        // Register the world unload listener
        getServer().getPluginManager().registerEvents(new WorldListener(this), this);

    }

    @Override
    public void onDisable() {

    }
}
