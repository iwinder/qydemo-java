package DesignPatterns.DesignConcept.Aggregator.v4;

import DesignPatterns.DesignConcept.Aggregator.v2.MetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestStat;
import DesignPatterns.DesignConcept.Aggregator.v3.Aggregator;
import DesignPatterns.DesignConcept.Aggregator.v3.StatViewer;

import java.util.List;
import java.util.Map;

public abstract class ScheduledReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public ScheduledReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected void doStartAndReport(long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, List<RequestInfo>> requestInfos =
                metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> stats = aggregator.aggregate(requestInfos,durationInMillis);

        // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）
        viewer.output(stats, startTimeInMillis, endTimeInMillis);

    }
}
