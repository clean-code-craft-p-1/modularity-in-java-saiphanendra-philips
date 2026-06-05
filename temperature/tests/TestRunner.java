package temperature.tests;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        if (args.length > 0 && "--help".equals(args[0])) {
            System.out.println("Runs all temperature module tests.");
            return;
        }

        new TemperatureLineParserTest().run();
        new TemperatureAnalyzerTest().run();
        new TemperatureProcessingServiceTest().run();
        new SampleDataFileTest().run();
        System.out.println("All tests passed");
    }
}
