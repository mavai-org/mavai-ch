package org.javai.ch;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.javai.newsroom.CurationResult;
import org.javai.newsroom.Newsroom;
import org.javai.newsroom.NewsroomConfig;
import org.javai.newsroom.feed.FeedMetadata;
import org.javai.newsroom.schedule.TierFilter;

public class Main {

    private static final FeedMetadata DEFAULT_FEED_METADATA = new FeedMetadata(
            "javai.ch \u2014 AI Regulation Feed",
            "Curated AI regulation news from Swiss and European sources",
            "https://javai.ch/",
            "feed.xml"
    );

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }

        var command = args[0];
        var params = parseArgs(args);

        var feedMetadata = new FeedMetadata(
                params.title != null ? params.title : DEFAULT_FEED_METADATA.title(),
                params.description != null ? params.description : DEFAULT_FEED_METADATA.description(),
                params.siteUrl != null ? params.siteUrl : DEFAULT_FEED_METADATA.siteUrl(),
                DEFAULT_FEED_METADATA.feedPath()
        );

        var config = new NewsroomConfig(
                Path.of(params.configDir),
                Path.of(params.feed),
                Path.of(params.output),
                feedMetadata
        );
        var newsroom = new Newsroom(config);

        switch (command) {
            case "curate" -> {
                var result = newsroom.curate(TierFilter.parseTiers(params.tiers), params.dryRun);
                if (result.hasStructuralFailures()) {
                    writeStructuralReport(result, Path.of(params.output));
                    System.exit(2);
                }
            }
            case "generate" -> {
                var tags = parseTags(params.tags);
                if (tags.isEmpty()) {
                    newsroom.generate();
                } else {
                    newsroom.generate(Path.of(params.output), feedMetadata, tags);
                }
            }
            default -> {
                System.err.println("Unknown command: " + command);
                printUsage();
                System.exit(1);
            }
        }
    }

    static Set<String> parseTags(String tagsArg) {
        if (tagsArg == null || tagsArg.isBlank()) return Set.of();
        return Arrays.stream(tagsArg.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Params parseArgs(String[] args) {
        var params = new Params();
        for (int i = 1; i < args.length; i++) {
            var arg = args[i];
            if (arg.startsWith("--config=")) {
                params.configDir = arg.substring("--config=".length());
            } else if (arg.startsWith("--feed=")) {
                params.feed = arg.substring("--feed=".length());
            } else if (arg.startsWith("--tiers=")) {
                params.tiers = arg.substring("--tiers=".length());
            } else if (arg.startsWith("--output=")) {
                params.output = arg.substring("--output=".length());
            } else if (arg.startsWith("--tags=")) {
                params.tags = arg.substring("--tags=".length());
            } else if (arg.startsWith("--title=")) {
                params.title = arg.substring("--title=".length());
            } else if (arg.startsWith("--description=")) {
                params.description = arg.substring("--description=".length());
            } else if (arg.startsWith("--site-url=")) {
                params.siteUrl = arg.substring("--site-url=".length());
            } else if (arg.equals("--dry-run")) {
                params.dryRun = true;
            }
        }
        return params;
    }

    private static void writeStructuralReport(CurationResult result, Path outputDir) {
        var sb = new StringBuilder();
        sb.append("## Structural check failures\n\n");
        sb.append("The following sources failed structural litmus tests during the latest curation run.\n");
        sb.append("This usually means the source page has been restructured and the scraper selectors need updating.\n\n");
        for (var failure : result.structuralFailures()) {
            sb.append("### ").append(failure.sourceName()).append(" (`").append(failure.sourceId()).append("`)\n\n");
            sb.append("- **URL:** ").append(failure.sourceUrl()).append("\n");
            sb.append("- **Details:** ").append(failure.details()).append("\n\n");
        }
        try {
            Files.createDirectories(outputDir);
            var reportPath = outputDir.resolve("structural-check-report.md");
            Files.writeString(reportPath, sb.toString());
            System.out.println("Structural check report written to " + reportPath);
        } catch (Exception e) {
            System.err.println("Failed to write structural check report: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.err.println("""
                Usage: javai-ch <command> [options]

                Commands:
                  curate    Fetch news, filter, and write candidates to feed.yml
                  generate  Generate RSS and JSON feeds from accepted items

                Options:
                  --config=<path>        Path to config directory (default: newsroom/config)
                  --feed=<path>          Path to feed.yml (default: newsroom/data/feed.yml)
                  --tiers=<list>         Comma-separated tier numbers to fetch (default: all)
                  --output=<path>        Output directory for generated files (default: build/site)
                  --tags=<list>          Comma-separated tags to filter items (default: all items)
                  --title=<text>         Feed title (overrides default)
                  --description=<text>   Feed description (overrides default)
                  --site-url=<url>       Site URL (overrides default)
                  --dry-run              Skip LLM relevance filter (for testing)
                """);
    }

    private static class Params {
        String configDir = "newsroom/config";
        String feed = "newsroom/data/feed.yml";
        String tiers = "";
        String output = "build/site";
        String tags = "";
        String title = null;
        String description = null;
        String siteUrl = null;
        boolean dryRun = false;
    }
}
