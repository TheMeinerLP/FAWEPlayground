package com.fastasyncworldedit.playground;

import com.fastasyncworldedit.playground.commands.CheckerBoardPatternCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Playground extends JavaPlugin {

    @Override
    public void onEnable() {
        getSLF4JLogger().warn("THIS PLUGIN DON'T HAVE PERMISSIONS CHECK AND REPRESENT SOME API EXAMPLES OR PROTOTYPING");
        // WorldEdit.getInstance().getEventBus().register(new TrackingEditSessionListener());
        Bukkit.getCommandMap().register("checkerboard", new CheckerBoardPatternCommand());
    }
}
