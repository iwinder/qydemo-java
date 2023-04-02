package DesignPatterns.DesignConcept.Aggregator.v3;


import DesignPatterns.DesignConcept.Aggregator.v2.MetricsCollector;
import DesignPatterns.DesignConcept.Aggregator.v2.MetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v2.RedisMetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;

public class PerFCounterTest {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();
        // 定时触发统计并将结果显示到终端
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60,60);

        // 定时触发统计并将结果显示到Email
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("qq@windcoder.com");
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据
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
