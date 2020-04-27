package DesignPatterns.DesignConcept.Aggregator.v3;

import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestStat;

import java.util.*;

/**
 * 工具类，负责各种统计数据的计算，比如响应时间的最大值、最小值、平均值、百分位值、接口访问次数、tps
 */
public class Aggregator {
    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();

        for (Map.Entry<String, List<RequestInfo>> entry: requestInfos.entrySet() ) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);

        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTImes = new ArrayList<>();
        for (RequestInfo requestInfo: requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTImes.add(respTime);
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTImes));
        requestStat.setMinResponseTime(min(respTImes));
        requestStat.setAvgResponseTime(avg(respTImes));
        requestStat.setP999ResponseTime(percentile999(respTImes));
        requestStat.setP99ResponseTime(percentile99(respTImes));
        requestStat.setCount(respTImes.size());
        requestStat.setTps((long) tps(respTImes.size(), durationInMillis/1000));
        return requestStat;
    }


    private double max(List<Double> dataset){
        double maxRespTime = Double.MIN_VALUE;
        for (double data: dataset) {
            if (maxRespTime < data) {
                maxRespTime = data;
            }
        }
      return maxRespTime;
    }

    private double min(List<Double> dataset) {
        double minRespTime = Double.MAX_VALUE;
        for (double data: dataset) {
            if (minRespTime > data) {
                minRespTime = data;
            }
        }
        return minRespTime;
    }

    private double avg(List<Double> dataset) {
        double avgRespTime = -1;
        double sumRespTIme = 0;
        if (!dataset.isEmpty()) {
            for (double data: dataset) {
                sumRespTIme += data;
            }
            avgRespTime = sumRespTIme / dataset.size();
        }
        return avgRespTime;
    }

    private double tps(int count, double duration) {
       return  (long) (count / duration);
    }

    private double percentile999(List<Double> dataset) {
        int idx999 = (int)(dataset.size() * 0.999);
        return dataset.get(idx999);
    }

    private double percentile99(List<Double> dataset) {
        int idx99 = (int)(dataset.size() * 0.99);
        return dataset.get(idx99);
    }

//    private double percentile(List<Double> dataset, double ration) {
//
//    }

}
