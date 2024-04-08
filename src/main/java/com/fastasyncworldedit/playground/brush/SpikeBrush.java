package com.fastasyncworldedit.playground.brush;

import com.google.gson.stream.JsonToken;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.command.tool.brush.Brush;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;

public class SpikeBrush implements Brush {

    private final int height;
    private final Player player;

    public SpikeBrush(int height, Player player) {
        this.height = height;
        this.player = player;
    }

    @Override
    public void build(EditSession editSession, BlockVector3 impact, Pattern pattern, double size) throws MaxChangedBlocksException {
        int range = (int)Math.ceil(size + height);
        var playerLocation = player.getLocation().toBlockPoint();
        BlockVector3 vector3 = impact.subtract(playerLocation).normalize();
        for (int x = -range; x < range; x++) {
            for (int y = -range; y < range; y++) {
                for (int z = -range; z < range; z++) {
                    BlockVector3 p = impact.add(x, y, z);
                    BlockVector3 relative = p.subtract(impact);
                    double heightAlongD = relative.dot(vector3);
                    if (heightAlongD >= 0 && heightAlongD <= height) {
                        double currentRadius = size * (1 - heightAlongD / height);
                        System.out.println(vector3.length());
                        double distanceFromAxis = (relative.cross(vector3).length()) / vector3.length();
                        if (distanceFromAxis <= currentRadius) {
                            pattern.applyBlock(p);
                        }
                    }
                }
            }
        }
    }
}
