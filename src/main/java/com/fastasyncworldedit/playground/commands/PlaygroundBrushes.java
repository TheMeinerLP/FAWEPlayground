package com.fastasyncworldedit.playground.commands;

import com.fastasyncworldedit.core.command.tool.brush.BrushSettings;
import com.fastasyncworldedit.core.configuration.Caption;
import com.fastasyncworldedit.playground.brush.SpikeBrush;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.command.argument.Arguments;
import com.sk89q.worldedit.command.tool.BrushTool;
import com.sk89q.worldedit.command.tool.brush.Brush;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.internal.expression.Expression;
import org.enginehub.piston.annotation.Command;
import org.enginehub.piston.annotation.CommandContainer;
import org.enginehub.piston.annotation.param.Arg;
import org.enginehub.piston.inject.InjectedValueAccess;
import org.enginehub.piston.inject.Key;

@CommandContainer
public class PlaygroundBrushes {

    @Command(
            name = "spikebrush",
            aliases = "/spikebrush",
            desc = "Spike brush"
    )
    @CommandPermissions("playground.spikebrush")
    public void spikeBrush(InjectedValueAccess context,
                           Player player,
                           LocalSession session,
                           @Arg(desc = "The pattern of blocks to set") Pattern pattern,
                           @Arg(desc = "The radius of the spike", def = "2") Expression radius,
                           @Arg(desc = "The height of spike", def = "1") int height
    ) throws WorldEditException {
        var brush = new SpikeBrush(height, player);
        BrushSettings bs = new BrushSettings();
        BrushTool tool = session.getBrushTool(player, true);
        if (tool != null) {
            BrushSettings currentContext = tool.getContext();
            if (currentContext != null) {
                Brush currentBrush = currentContext.getBrush();
                if (currentBrush != null && currentBrush.getClass() == brush.getClass()) {
                    bs = currentContext;
                }
            }
        }
        Arguments arguments = context.injectedValue(Key.of(Arguments.class)).orElse(null);
        if (arguments != null) {
            String args = arguments.get();
            bs.addSetting(BrushSettings.SettingType.BRUSH, args.substring(args.indexOf(' ') + 1));
        }
        bs.addPermission("playground.spikebrush");
        bs.setBrush(brush);
        bs.setFill(pattern);
        bs.setSize(radius);
        if (tool != null) {
            tool.setPrimary(bs);
            tool.setSecondary(bs);
            player.print(
                    Caption.of("fawe.worldedit.brush.brush.equipped", arguments.get().split(" ")[1]));
        }

    }

}
