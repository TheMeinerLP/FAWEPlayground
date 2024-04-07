package com.fastasyncworldedit.playground.parser;

import com.fastasyncworldedit.playground.masks.StoneToolMask;
import com.google.common.collect.ImmutableList;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.internal.registry.SimpleInputParser;

import java.util.List;

public class StoneToolMaskParser extends SimpleInputParser<Mask> {

    private final List<String> aliases = ImmutableList.of("#stonetool");
    public StoneToolMaskParser(WorldEdit worldEdit) {
        super(worldEdit);
    }

    @Override
    public Mask parseFromSimpleInput(String input, ParserContext context) throws InputParseException {
        return new StoneToolMask(context.requireExtent());
    }

    @Override
    public List<String> getMatchedAliases() {
        return aliases;
    }
}
