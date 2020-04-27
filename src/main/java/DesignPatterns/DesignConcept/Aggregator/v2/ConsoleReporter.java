package DesignPatterns.DesignConcept.Aggregator.v2;

import Utills.PrintUtill;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 相当于上帝类，定时根据给定的时间区间，从数据库中取出数据，借助Aggreator类完成统计工作，并将统计结果输出到相应终端，比如命令行、Email
 */
public class ConsoleReporter {

    private MetricsStorage metricsStorage;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的世界区间，从数据库中拉取数据
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMills = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMills, endTimeInMillis);
                Map<String, RequestStat> stats = new HashMap<>();
                for (Map.Entry<String, List<RequestInfo>> entry: requestInfos.entrySet()) {
                    String apiName = entry.getKey();
                    List<RequestInfo> requestInfosPerApi = entry.getValue();
                    // 第2个代码逻辑：根据原始数据，计算得到统计数据
                    RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi,durationInMillis);
                    stats.put(apiName, requestStat);
                }
                // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）
                PrintUtill.println("Time Span：[" + startTimeInMills + "，" + endTimeInMillis + "]" );
                Gson  gson = new Gson();
                PrintUtill.println(gson.toJson(stats));

            }
        },0,periodInSeconds, TimeUnit.SECONDS);
    }
}
