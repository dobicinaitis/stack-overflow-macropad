package dev.dobicinaitis.macropad.cli.providers;

import picocli.CommandLine.Command;

@Command(
        name = "macropad",
        headerHeading = "@|bold NAME|@%n      macropad - The Key companion app%n",
        synopsisHeading = "%n@|bold USAGE|@%n      ",
        descriptionHeading = "%n@|bold DESCRIPTION|@%n      ",
        description = "Find the best answer to your technical issue with a click of a key.%n      " +
                "Simply copy an error message and hit The Key to view Stack Overflow search results in your default browser.",
        parameterListHeading = "%n@|bold PARAMETERS|@%n",
        optionListHeading = "%n@|bold OPTIONS|@%n",
        mixinStandardHelpOptions = true,
        usageHelpAutoWidth = true,
        versionProvider = VersionProvider.class
)
public class HelpProvider {
}
