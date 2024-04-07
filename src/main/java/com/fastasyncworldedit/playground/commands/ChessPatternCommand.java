package com.fastasyncworldedit.playground.commands;

import com.fastasyncworldedit.core.function.pattern.Linear3DBlockPattern;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChessPatternCommand extends Command {

    private static final Pattern pattern = new Linear3DBlockPattern(new Pattern[]{ BlockTypes.WHITE_WOOL, BlockTypes.BLACK_WOOL }, 2, 2, 2);
    public ChessPatternCommand() {
        super("chess", "generate a chess platform", "/chess", new ArrayList<>());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            var actor = BukkitAdapter.adapt(player);
            actor.runAsyncIfFree(() -> {
                Region selection = actor.getSelection();
                try (var editSession = actor.getSession().createEditSession(actor)) {
                    editSession.setBlocks(selection, pattern);
                }
            });
        }
        return false;
    }
}
