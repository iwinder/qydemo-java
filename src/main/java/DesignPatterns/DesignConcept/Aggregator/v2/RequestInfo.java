package DesignPatterns.DesignConcept.Aggregator.v2;

import lombok.Data;

@Data
public class RequestInfo {
    private String apiName;
    private Double responseTime;
    private Long timestamp;


    public RequestInfo(String apiName, Double responseTime, Long timestamp) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;

    }
}
