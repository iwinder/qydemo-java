package DesignPatterns.DesignConcept.Aggregator.v2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 工具类，负责各种统计数据的计算，比如响应时间的最大值、最小值、平均值、百分位值、接口访问次数、tps
 */
public class Aggregator {
    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        double maxRespTime = Double.MIN_VALUE;
        double minRespTime = Double.MAX_VALUE;
        double avgRespTime = -1;
        double p999RespTime = -1;
        double p99RespTime = -1;
        double sumRespTIme = 0;
        long count = 0;

        for (RequestInfo requestInfo : requestInfos) {
            ++count;
            double respTIme = requestInfo.getResponseTime();
            if (maxRespTime < respTIme) {
                maxRespTime = respTIme;
            }
            if (minRespTime > respTIme) {
                minRespTime = respTIme;
            }
            sumRespTIme += respTIme;
        }

        if (count != 0) {
            avgRespTime = sumRespTIme / count;
        }

        long tps = (long) (count / durationInMillis * 1000);
        Collections.sort(requestInfos, new Comparator<RequestInfo>() {
            @Override
            public int compare(RequestInfo o1, RequestInfo o2) {
                double diff = o1.getResponseTime() - o2.getResponseTime();
                if (diff < 0.0) {
                    return -1;
                } else if (diff > 0.0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        int idx999 = (int)(count * 0.999);
        int idx99 = (int)(count * 0.99);
        if (count !=0 ) {
            p999RespTime = requestInfos.get(idx999).getResponseTime();
            p99RespTime = requestInfos.get(idx99).getResponseTime();
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setCount(count);
        requestStat.setTps(tps);
        return requestStat;
    }
}
