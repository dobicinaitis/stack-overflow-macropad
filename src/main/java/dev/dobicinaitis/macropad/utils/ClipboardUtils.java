package dev.dobicinaitis.macropad.utils;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

@Singleton
@Slf4j
public class ClipboardUtils {

    private final Clipboard clipboard;
    private final PrintStream standardErrorOutput;
    private final PrintStream dummyErrorOutput;

    public ClipboardUtils() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        this.standardErrorOutput = System.err;
        this.dummyErrorOutput = new PrintStream(new OutputStream() {
            public void write(int b) {
            }
        });
    }

    /**
     * Get the cut/copied text from clipboard
     * @return String content from clipboard
     */
    public String getText() {
        try {
            // Workaround to avoid stderr outputs when using text copied from IntelliJ:
            // https://stackoverflow.com/q/63212033
            System.setErr(dummyErrorOutput);
            return clipboard.getData(DataFlavor.stringFlavor).toString();
        } catch (UnsupportedFlavorException | IOException e) {
            System.setErr(standardErrorOutput);
            log.error("Could not get clipboard content: {}", e.getMessage());
        } finally {
            System.setErr(standardErrorOutput);
        }
        return null;
    }
}
