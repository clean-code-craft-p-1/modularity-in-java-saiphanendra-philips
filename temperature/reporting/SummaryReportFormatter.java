package temperature.reporting;

import temperature.domain.ProcessingResult;
import temperature.domain.TemperatureSummary;

public class SummaryReportFormatter {
    public String formatSummaryReport(String inputFileName, ProcessingResult result) {
        StringBuilder builder = new StringBuilder();
        builder.append("Temperature Analysis Summary").append(System.lineSeparator());
        builder.append("==================================================").append(System.lineSeparator());
        builder.append("File analyzed: ").append(inputFileName).append(System.lineSeparator());
        builder.append("Total readings: ").append(result.getTotalReadings()).append(System.lineSeparator());
        builder.append("Valid readings: ").append(result.getValidReadings()).append(System.lineSeparator());
        builder.append("Errors: ").append(result.getInvalidReadingCount()).append(System.lineSeparator());

        if (result.hasSummary()) {
            TemperatureSummary summary = result.getSummary();
            builder.append(String.format("Max temperature: %.2f%n", summary.getMax()));
            builder.append(String.format("Min temperature: %.2f%n", summary.getMin()));
            builder.append(String.format("Average temperature: %.2f%n", summary.getAverage()));
        }

        if (!result.getInvalidLines().isEmpty()) {
            builder.append("Invalid lines:").append(System.lineSeparator());
            for (String invalidLine : result.getInvalidLines()) {
                builder.append(invalidLine).append(System.lineSeparator());
            }
        }

        return builder.toString();
    }
}
