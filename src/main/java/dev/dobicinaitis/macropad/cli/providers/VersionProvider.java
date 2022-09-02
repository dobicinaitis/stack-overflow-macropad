package dev.dobicinaitis.macropad.cli.providers;

import lombok.SneakyThrows;
import picocli.CommandLine;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class VersionProvider implements CommandLine.IVersionProvider {

    private static final String MANIFEST_RESOURCE = "META-INF/MANIFEST.MF";
    private static final String VERSION_PROPERTY = "Application-Version";
    private static final String BUILD_PROPERTY = "Application-Build";

    @SneakyThrows
    public String[] getVersion() {
        final Manifest manifest = new Manifest();
        manifest.read(getClass().getClassLoader().getResourceAsStream(MANIFEST_RESOURCE));
        final Attributes attributes = manifest.getMainAttributes();
        final String version = attributes.getValue(VERSION_PROPERTY);
        final String build = attributes.getValue(BUILD_PROPERTY);
        final String output = String.format("${COMMAND-FULL-NAME} version %s, build %s", version, build);
        return new String[]{output};
    }
}
