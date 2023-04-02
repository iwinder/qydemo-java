package DesignPatterns.DesignConcept.Aggregator.v2;

import java.util.List;
import java.util.Map;

/**
 * 负责原始数据的存储和读取
 */
public class RedisMetricsStorage implements MetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMills, long endTimeInMills) {
        return null;
    }
}
