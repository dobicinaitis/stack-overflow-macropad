package dev.dobicinaitis.macropad.cli.commands;

import ch.qos.logback.classic.Level;
import com.tulskiy.keymaster.common.Provider;
import dev.dobicinaitis.macropad.cli.providers.HelpProvider;
import dev.dobicinaitis.macropad.utils.ClipboardUtils;

import io.micronaut.context.annotation.Value;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Command
@Slf4j
public class SearchCommand extends HelpProvider implements Runnable {

    @Inject
    private ClipboardUtils clipboard;

    @Option(names = {"-v", "--verbose"}, description = "Print debug information.")
    private boolean verbose;

    @Option(names = {"-d", "--daemon"}, description = "Wait for The Key to be pressed, then open Stack Overflow search results with query from your clipboard.")
    private boolean daemon;

    @Parameters(paramLabel = "<search text>", defaultValue = "", description = "Search query (optional). Clipboard content will be used if parameter is omitted.")
    private String searchQuery;

    @Value("${search.hotkey}")
    private String searchHotkey;

    @Value("${search.url}")
    private String searchUrl;

    public void run() {

        if (verbose) {
            changeLogLevel(Level.DEBUG);
        }

        if (daemon) {
            log.debug("Daemon mode started. Monitoring for keyboard shortcut: {}", getPrettyHotkey());
            final Provider provider = Provider.getCurrentProvider(false);
            provider.register(KeyStroke.getKeyStroke(searchHotkey), hotKey -> {
                log.debug("{} was pressed", getPrettyHotkey());
                searchQuery = clipboard.getText();
                if (StringUtils.isEmpty(searchQuery)) {
                    log.debug("No text in clipboard");
                } else {
                    openSearchQueryInDefaultBrowser(searchQuery);
                }
            });
        } else {
            final String query = StringUtils.isNotEmpty(searchQuery) ? searchQuery : clipboard.getText();
            if (StringUtils.isEmpty(query)) {
                log.debug("Nothing to do. Search criteria was not provided as input parameter and clipboard was empty.");
                CommandLine.usage(new SearchCommand(), System.out);
                return;
            }
            openSearchQueryInDefaultBrowser(query);
        }
    }

    private void changeLogLevel(Level level) {
        final Logger rootLogger = (Logger) LoggerFactory.getLogger(this.getClass().getPackageName());
        rootLogger.setLevel(level);
    }

    @SneakyThrows
    private void openSearchQueryInDefaultBrowser(String searchQuery) {
        log.debug("Search query: {}", searchQuery);
        if (StringUtils.isNotEmpty(searchQuery)) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI(searchUrl + URLEncoder.encode(searchQuery, StandardCharsets.UTF_8));
                log.debug("Opening URL: {}", uri);
                Desktop.getDesktop().browse(uri);
            } else {
                log.info("Action to open an URL in the default browser is not supported on your OS.");
            }
        }
    }

    private String getPrettyHotkey() {
        return searchHotkey
                .trim()
                .toUpperCase()
                .replace(" ", "+");
    }
}
