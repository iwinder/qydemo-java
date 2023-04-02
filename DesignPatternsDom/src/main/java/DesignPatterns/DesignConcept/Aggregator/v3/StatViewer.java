package DesignPatterns.DesignConcept.Aggregator.v3;


import DesignPatterns.DesignConcept.Aggregator.v2.RequestStat;

import java.util.Map;

public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMillis);
}
