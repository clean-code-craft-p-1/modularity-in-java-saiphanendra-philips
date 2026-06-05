package temperature;

import temperature.analysis.TemperatureAnalyzer;
import temperature.application.TemperatureProcessingService;
import temperature.domain.ProcessingResult;
import temperature.io.FileReadingSource;
import temperature.io.FileReportSink;
import temperature.parsing.TemperatureLineParser;
import temperature.reporting.ConsoleSummaryPresenter;
import temperature.reporting.SummaryReportFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            String inputFile = SampleDataFile.create();
            ProcessingResult result = buildProcessor().processFile(inputFile);
            new ConsoleSummaryPresenter().present(result);
            saveReport(inputFile, result);
            cleanupFiles(inputFile, inputFile + "_summary.txt");
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Processing failed", exception);
        }
    }

    private static TemperatureProcessingService buildProcessor() {
        return new TemperatureProcessingService(
                new FileReadingSource(),
                new TemperatureLineParser(),
                new TemperatureAnalyzer()
        );
    }

    private static void saveReport(String inputFile, ProcessingResult result) throws IOException {
        String outputFile = inputFile + "_summary.txt";
        String report = new SummaryReportFormatter().formatSummaryReport(inputFile, result);
        new FileReportSink().writeReport(outputFile, report);
        LOGGER.log(Level.INFO, "Report saved to {0}", outputFile);
    }

    private static void cleanupFiles(String inputFile, String reportFile) {
        try {
            Files.deleteIfExists(Path.of(inputFile));
            Files.deleteIfExists(Path.of(reportFile));
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, "Cleanup failed", exception);
        }
    }
}