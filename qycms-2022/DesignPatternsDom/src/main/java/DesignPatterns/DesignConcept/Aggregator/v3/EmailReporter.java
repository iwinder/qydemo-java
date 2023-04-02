package DesignPatterns.DesignConcept.Aggregator.v3;


import DesignPatterns.DesignConcept.Aggregator.v2.MetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestStat;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmailReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
//    private ScheduledExecutorService executor;

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
//        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMills = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMills, endTimeInMillis);
                Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInMillis);
                // TODO: 格式化为html格式，并发送邮件
                viewer.output(stats, startTimeInMills, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000 );
    }
}
