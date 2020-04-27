package DesignPatterns.DesignConcept.Aggregator.v2;

public class Demo {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        consoleReporter.startRepeatedReport(60,60);

        EmailReporter emailReporter = new EmailReporter(storage);
        emailReporter.addToAddress("qq@qq.com");
        emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123d, 10234l));
        collector.recordRequest(new RequestInfo("register", 223d, 11234l));
        collector.recordRequest(new RequestInfo("register", 323d, 12234l));
        collector.recordRequest(new RequestInfo("login", 23d, 12434l));
        collector.recordRequest(new RequestInfo("login", 1223d, 12434l));

        try{
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
