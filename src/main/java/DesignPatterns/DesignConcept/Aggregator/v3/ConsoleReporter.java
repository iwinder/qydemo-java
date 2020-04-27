package DesignPatterns.DesignConcept.Aggregator.v3;


import DesignPatterns.DesignConcept.Aggregator.v2.MetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestStat;
import Utills.PrintUtill;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的世界区间，从数据库中拉取数据
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                Map<String, RequestStat> stats = aggregator.aggregate(requestInfos,durationInMillis);

                // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）
                viewer.output(stats, startTimeInMillis, endTimeInMillis);

            }
        },0L,periodInSeconds, TimeUnit.SECONDS);
    }
}
