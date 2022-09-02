package dev.dobicinaitis.macropad;

import dev.dobicinaitis.macropad.cli.commands.SearchCommand;
import io.micronaut.configuration.picocli.PicocliRunner;

public class MacropadApplication {
    public static void main(String[] args) {
        PicocliRunner.run(SearchCommand.class, args);
    }
}
