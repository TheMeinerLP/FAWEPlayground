package com.fastasyncworldedit.playground.masks;

import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.mask.AbstractMask;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockCategories;

public class StoneToolMask extends AbstractMask {

    private final Extent extent;

    public StoneToolMask(Extent extent) {
        this.extent = extent;
    }

    @Override
    public boolean test(BlockVector3 vector) {
        return BlockCategories.NEEDS_STONE_TOOL.contains(vector.getBlock(extent));
    }

    @Override
    public Mask copy() {
        return new StoneToolMask(extent);
    }
}
