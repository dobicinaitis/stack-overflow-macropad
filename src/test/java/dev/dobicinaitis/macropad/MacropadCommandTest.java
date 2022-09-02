package dev.dobicinaitis.macropad;

import dev.dobicinaitis.macropad.cli.commands.SearchCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MacropadCommandTest {

    @Test
    public void testWithCommandLineOption() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
            String[] args = new String[]{"-h"};
            PicocliRunner.run(SearchCommand.class, ctx, args);
            assertTrue(baos.toString().contains("macropad"));
        }
    }
}
