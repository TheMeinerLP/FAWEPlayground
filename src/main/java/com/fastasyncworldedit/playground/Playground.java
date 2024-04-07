package com.fastasyncworldedit.playground;

import com.fastasyncworldedit.core.FaweAPI;
import com.fastasyncworldedit.playground.commands.CheckerBoardPatternCommand;
import com.fastasyncworldedit.playground.parser.CheckerboardPatternParser;
import com.sk89q.worldedit.WorldEdit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Playground extends JavaPlugin {

    @Override
    public void onEnable() {
        getSLF4JLogger().warn("THIS PLUGIN DON'T HAVE PERMISSIONS CHECK AND REPRESENT SOME API EXAMPLES OR PROTOTYPING");
        // WorldEdit.getInstance().getEventBus().register(new TrackingEditSessionListener());
        Bukkit.getCommandMap().register("checkerboard", new CheckerBoardPatternCommand());
        WorldEdit.getInstance().getPatternFactory().register(new CheckerboardPatternParser(WorldEdit.getInstance()));
    }
}
