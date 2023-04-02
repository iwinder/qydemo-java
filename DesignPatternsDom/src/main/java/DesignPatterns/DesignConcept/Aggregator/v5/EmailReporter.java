package DesignPatterns.DesignConcept.Aggregator.v5;

import DesignPatterns.DesignConcept.Aggregator.v2.MetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v2.RedisMetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v3.Aggregator;
import DesignPatterns.DesignConcept.Aggregator.v5.EmailViewer;
import DesignPatterns.DesignConcept.Aggregator.v3.StatViewer;
import DesignPatterns.DesignConcept.Aggregator.v4.ScheduledReporter;

import java.util.*;

public class EmailReporter extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
//    private ScheduledExecutorService executor;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public EmailReporter(List<String> emailToAddresses) {
        this(new RedisMetricsStorage(), new Aggregator(), new EmailViewer(emailToAddresses));
    }

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
//        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay() ;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMills = endTimeInMillis - durationInMillis;
                doStartAndReport(startTimeInMills, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000 );
    }

    protected Date trimTimeFieldsToZeroOfNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
