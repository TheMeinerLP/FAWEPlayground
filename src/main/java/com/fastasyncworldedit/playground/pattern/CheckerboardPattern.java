package com.fastasyncworldedit.playground.pattern;

import com.fastasyncworldedit.core.function.pattern.Linear3DBlockPattern;
import com.sk89q.worldedit.function.pattern.AbstractPattern;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockTypes;

public class CheckerboardPattern extends AbstractPattern {

    private static final Pattern pattern = new Linear3DBlockPattern(new Pattern[]{ BlockTypes.WHITE_WOOL, BlockTypes.BLACK_WOOL }, 2, 2, 2);
    @Override
    public BaseBlock applyBlock(BlockVector3 position) {
        return pattern.applyBlock(position);
    }
}
