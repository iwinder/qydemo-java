package DesignPatterns.DesignConcept.Aggregator.v5;

import DesignPatterns.DesignConcept.Aggregator.v2.MetricsStorage;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import com.google.common.eventbus.Subscribe;

public class EventListener {
    private MetricsStorage metricsStorage;

    public EventListener(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    @Subscribe
    public void saveRequestInfo(MetricsStorage metricsStorage, RequestInfo requestInfo) {
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
