package com.fastasyncworldedit.playground.extents;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.AbstractDelegateExtent;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.util.Set;

public class TrackingExtent extends AbstractDelegateExtent {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(TrackingExtent.class);
    public TrackingExtent(Extent extent) {
        super(extent);
    }

    @Override
    public <B extends BlockStateHolder<B>> int setBlocks(Region region, B block) throws MaxChangedBlocksException {
        LOGGER.warn("setBlocks: {}", block.getBlockType().toString());
        return super.setBlocks(region, block);
    }

    @Override
    public int setBlocks(Region region, Pattern pattern) throws MaxChangedBlocksException {
        LOGGER.warn("setBlocks: {}", pattern.getClass().getSimpleName());
        return super.setBlocks(region, pattern);
    }

    @Override
    public int setBlocks(Set<BlockVector3> vset, Pattern pattern) {
        LOGGER.warn("setBlocks: {}", pattern.getClass().getSimpleName());
        return super.setBlocks(vset, pattern);
    }

    @Override
    public <T extends BlockStateHolder<T>> boolean setBlock(BlockVector3 position, T block) throws WorldEditException {
        LOGGER.warn("setBlock: {}", block.getBlockType().toString());
        return super.setBlock(position, block);
    }

    @Override
    public <T extends BlockStateHolder<T>> boolean setBlock(int x, int y, int z, T block) throws WorldEditException {
        LOGGER.warn("setBlock: {}", block.getBlockType().toString());
        return super.setBlock(x, y, z, block);
    }
}
