package com.fastasyncworldedit.playground;

import com.fastasyncworldedit.playground.commands.CheckerBoardPatternCommand;
import com.fastasyncworldedit.playground.commands.PlaygroundBrushes;
import com.fastasyncworldedit.playground.commands.PlaygroundBrushesRegistration;
import com.fastasyncworldedit.playground.parser.CheckerboardPatternParser;
import com.fastasyncworldedit.playground.parser.StoneToolMaskParser;
import com.google.common.collect.ImmutableList;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.Capability;
import com.sk89q.worldedit.extension.platform.PlatformCommandManager;
import com.sk89q.worldedit.extension.platform.PlatformManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Playground extends JavaPlugin {

    @Override
    public void onEnable() {
        getSLF4JLogger().warn("THIS PLUGIN DON'T HAVE PERMISSIONS CHECK AND REPRESENT SOME API EXAMPLES OR PROTOTYPING");
        // WorldEdit.getInstance().getEventBus().register(new TrackingEditSessionListener());
        Bukkit.getCommandMap().register("checkerboard", new CheckerBoardPatternCommand(this));
        WorldEdit.getInstance().getPatternFactory().register(new CheckerboardPatternParser(WorldEdit.getInstance()));
        WorldEdit.getInstance().getMaskFactory().register(new StoneToolMaskParser(WorldEdit.getInstance()));
        PlatformManager platformManager = WorldEdit.getInstance().getPlatformManager();
        PlatformCommandManager platformCommandManager = platformManager.getPlatformCommandManager();
        platformCommandManager.registration.register(platformCommandManager.getCommandManager(), PlaygroundBrushesRegistration.builder(), new PlaygroundBrushes());
        platformManager.queryCapability(Capability.USER_COMMANDS).registerCommands(platformCommandManager.getCommandManager());
    }
}
