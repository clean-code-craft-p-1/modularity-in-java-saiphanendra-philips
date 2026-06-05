package temperature.application;

import temperature.analysis.TemperatureAnalyzer;
import temperature.domain.ProcessingResult;
import temperature.domain.TemperatureReading;
import temperature.domain.TemperatureSummary;
import temperature.io.ReadingSource;
import temperature.parsing.LineParseResult;
import temperature.parsing.TemperatureLineParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemperatureProcessingService {
    private final ReadingSource readingSource;
    private final TemperatureLineParser parser;
    private final TemperatureAnalyzer analyzer;

    public TemperatureProcessingService(
            ReadingSource readingSource,
            TemperatureLineParser parser,
            TemperatureAnalyzer analyzer
    ) {
        this.readingSource = readingSource;
        this.parser = parser;
        this.analyzer = analyzer;
    }

    public ProcessingResult processFile(String inputFileName) throws IOException {
        List<String> lines = readingSource.readLinesFrom(inputFileName);
        List<TemperatureReading> validReadings = new ArrayList<>();
        List<String> invalidLines = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            LineParseResult parseResult = parser.parseReadingLine(i + 1, lines.get(i));
            if (parseResult.isValid()) {
                validReadings.add(parseResult.getReading());
            } else {
                invalidLines.add(parseResult.getError());
            }
        }

        TemperatureSummary summary = validReadings.isEmpty() ? null : analyzer.calculateSummary(validReadings);
        return new ProcessingResult(
                lines.size(),
                validReadings.size(),
                invalidLines.size(),
                invalidLines,
                summary
        );
    }
}
