package com.fastasyncworldedit.playground.parser;

import com.fastasyncworldedit.core.extension.factory.parser.AliasedParser;
import com.fastasyncworldedit.core.extension.factory.parser.RichParser;
import com.fastasyncworldedit.playground.pattern.CheckerboardPattern;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.internal.registry.InputParser;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CheckerboardPatternParser extends InputParser<Pattern> implements AliasedParser {


    private static final List<String> ALIAS = List.of("#checkerboard");

    /**
     * Create a new rich parser with a defined prefix for the result, e.g. {@code #simplex}.
     *
     * @param worldEdit the worldedit instance.
     */
    public CheckerboardPatternParser(WorldEdit worldEdit) {
        super(worldEdit);
    }

    @Override
    public Stream<String> getSuggestions(String input, ParserContext context) {
        return ALIAS.stream().filter(validPrefix(input));
    }

    private Predicate<String> validPrefix(String other) {
        return prefix -> {
            if (prefix.length() > other.length()) {
                return prefix.startsWith(other);
            }
            return false;
        };
    }

    @Override
    public Pattern parseFromInput(String input, ParserContext context) throws InputParseException {
        return new CheckerboardPattern();
    }

    @Override
    public List<String> getMatchedAliases() {
        return ALIAS;
    }
}
