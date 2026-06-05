package temperature.domain;

public class TemperatureSummary {
    private final double max;
    private final double min;
    private final double average;

    public TemperatureSummary(double max, double min, double average) {
        this.max = max;
        this.min = min;
        this.average = average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAverage() {
        return average;
    }
}

