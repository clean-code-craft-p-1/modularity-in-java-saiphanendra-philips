package temperature;

import temperature.analysis.TemperatureAnalyzer;
import temperature.application.TemperatureProcessingService;
import temperature.domain.ProcessingResult;
import temperature.io.FileReadingSource;
import temperature.io.FileReportSink;
import temperature.io.ReportSink;
import temperature.parsing.TemperatureLineParser;
import temperature.reporting.ConsoleSummaryPresenter;
import temperature.reporting.SummaryReportFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFile = createSampleDataFile();
        if (inputFile == null) {
            return;
        }

        TemperatureProcessingService processor = new TemperatureProcessingService(
                new FileReadingSource(),
                new TemperatureLineParser(),
                new TemperatureAnalyzer()
        );

        try {
            ProcessingResult result = processor.processFile(inputFile);
            new ConsoleSummaryPresenter().present(result);

            String outputFile = inputFile + "_summary.txt";
            String report = new SummaryReportFormatter().formatSummaryReport(inputFile, result);
            ReportSink sink = new FileReportSink();
            sink.writeReport(outputFile, report);
            System.out.println("Report saved to " + outputFile);

            verifyReportFile(outputFile);
            cleanupFiles(inputFile, outputFile);
        } catch (IOException exception) {
            System.out.println("Processing failed: " + exception.getMessage());
        }
    }

    private static String createSampleDataFile() {
        String testFilename = "test_temps.csv";
        List<String> testData = List.of(
                "09:15:30,23.5",
                "09:16:00,24.1",
                "09:16:30,22.8",
                "09:17:00,25.3",
                "09:17:30,23.9",
                "09:18:00,24.7",
                "09:18:30,22.4",
                "09:19:00,26.1",
                "09:19:30,23.2",
                "09:20:00,25.0"
        );

        try {
            Files.write(Path.of(testFilename), testData);
            System.out.println("Created test file: " + testFilename);
            return testFilename;
        } catch (IOException exception) {
            System.out.println("Error creating test file: " + exception.getMessage());
            return null;
        }
    }

    private static void verifyReportFile(String reportFile) {
        Path reportPath = Path.of(reportFile);
        if (!Files.exists(reportPath)) {
            return;
        }

        try {
            String content = Files.readString(reportPath);
            assert content.contains("Total readings: 10") : "Total readings assertion failed";
            assert content.contains("Valid readings: 10") : "Valid readings assertion failed";
            assert content.contains("Errors: 0") : "Errors assertion failed";
            System.out.println("Summary file created: " + reportFile);
            System.out.println("Summary file contents verified");
        } catch (IOException exception) {
            System.out.println("Error reading summary file: " + exception.getMessage());
        }
    }

    private static void cleanupFiles(String inputFile, String reportFile) {
        try {
            Files.deleteIfExists(Path.of(inputFile));
            Files.deleteIfExists(Path.of(reportFile));
        } catch (IOException exception) {
            System.out.println("Error cleaning up files: " + exception.getMessage());
        }
    }
}