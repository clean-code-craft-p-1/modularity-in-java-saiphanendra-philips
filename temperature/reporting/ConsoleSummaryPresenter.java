package temperature.reporting;

import temperature.domain.ProcessingResult;
import temperature.domain.TemperatureSummary;

public class ConsoleSummaryPresenter {
    public void present(ProcessingResult result) {
        System.out.println("============================================================");
        System.out.println("Temperature Analysis Summary");
        System.out.println("============================================================");
        System.out.println("Total readings: " + result.getTotalReadings());
        System.out.println("Valid readings: " + result.getValidReadings());
        System.out.println("Errors: " + result.getInvalidReadingCount());
        System.out.println("------------------------------------------------------------");

        if (result.hasSummary()) {
            TemperatureSummary summary = result.getSummary();
            System.out.printf("Max temperature: %.2f%n", summary.getMax());
            System.out.printf("Min temperature: %.2f%n", summary.getMin());
            System.out.printf("Average temperature: %.2f%n", summary.getAverage());
            System.out.println("------------------------------------------------------------");
        } else {
            System.out.println("No valid temperature data found.");
        }

        if (!result.getInvalidLines().isEmpty()) {
            System.out.println("Invalid lines:");
            for (String invalidLine : result.getInvalidLines()) {
                System.out.println(invalidLine);
            }
        }
    }
}
