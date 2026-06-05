package temperature.tests;

import temperature.parsing.LineParseResult;
import temperature.parsing.TemperatureLineParser;

public class TemperatureLineParserTest {
    private final TemperatureLineParser parser = new TemperatureLineParser();

    public void run() {
        parsesValidLine();
        rejectsMalformedTimestamp();
        rejectsOutOfRangeTemperature();
    }

    private void parsesValidLine() {
        LineParseResult result = parser.parseReadingLine(1, "09:15:30,23.5");
        assertTrue(result.isValid(), "Expected valid parse result");
        assertEquals(23.5, result.getReading().getValue(), "Expected temperature value to match");
        assertEquals("09:15:30", result.getReading().getTimestamp().toString(), "Expected timestamp to match");
    }

    private void rejectsMalformedTimestamp() {
        LineParseResult result = parser.parseReadingLine(2, "09:15,23.5");
        assertFalse(result.isValid(), "Expected malformed timestamp to be rejected");
    }

    private void rejectsOutOfRangeTemperature() {
        LineParseResult result = parser.parseReadingLine(3, "09:15:30,300.0");
        assertFalse(result.isValid(), "Expected out-of-range temperature to be rejected");
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }

    private void assertEquals(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.0001) {
            throw new AssertionError(message + ": expected " + expected + " but got " + actual);
        }
    }

    private void assertEquals(String expected, String actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ": expected " + expected + " but got " + actual);
        }
    }
}
