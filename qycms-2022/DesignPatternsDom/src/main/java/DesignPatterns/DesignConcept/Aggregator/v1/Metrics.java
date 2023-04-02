package DesignPatterns.DesignConcept.Aggregator.v1;

import Utills.PrintUtill;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class Metrics {
    private Map<String, List<Double>> reponseTimes = new HashMap<>();
    private Map<String, List<Double>> timestamps = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    /**
     *  记录接口请求的响应时间
     * @param apiName
     * @param reponseTime
     */
    public void recordResponseTime(String apiName, double reponseTime) {
        reponseTimes.putIfAbsent(apiName,  new ArrayList<>());
        reponseTimes.get(apiName).add(reponseTime);
    }

    /**
     * 记录接口请求的访问时间
     * @param apiName
     * @param timestamp
     */
    public void recordTimestamps(String apiName, double timestamp) {
        timestamps.putIfAbsent(apiName, new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    /**
     * 以指定的频率统计数据并输出结果
     * @param period
     * @param unit
     */
    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Map<String, Map<String, Double>> stats = new HashMap<>();
                for (Map.Entry<String, List<Double>> entry : reponseTimes.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiRespTimes = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("max", max(apiRespTimes));
                    stats.get(apiName).put("avg", avg(apiRespTimes));
                }

                for(Map.Entry<String, List<Double>> entry: timestamps.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiTimestamps = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("count", (double) apiTimestamps.size());
                }
                PrintUtill.println(gson.toJson(stats));
            }
        },0, period, unit);
    }

    private Double avg(List<Double> apiRespTimes) {
        Double sum = apiRespTimes.stream().mapToDouble(e -> e).average().getAsDouble();
        return sum;
    }

    private Double max(List<Double> apiRespTimes) {
//        Double max  = apiRespTimes.get(0);
//        for (Double enty: apiRespTimes) {
//            if (enty > max) {
//                max  = enty;
//            }
//        }
        // https://blog.csdn.net/weixin_39643007/article/details/90703329
// int maxa =  listUsers.stream().map(e -> e.getAge()).reduce(Integer::max).get();
        Double max = apiRespTimes.stream().mapToDouble(e -> e).max().getAsDouble();

        return max;
    }




}
