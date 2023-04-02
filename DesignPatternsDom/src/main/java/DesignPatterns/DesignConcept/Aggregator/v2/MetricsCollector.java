package DesignPatterns.DesignConcept.Aggregator.v2;


import org.apache.commons.lang3.StringUtils;

/**
 * 负责打点采集原始数据，包括记录每次接口请求的响应时间和请求时间戳，并调用MetricsStoraget提供的接口来存储数据，
 */
public class MetricsCollector {
    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }

        metricsStorage.saveRequestInfo(requestInfo);
    }
}
