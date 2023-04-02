package DesignPatterns.DesignConcept.Aggregator.v2;

import java.util.List;
import java.util.Map;

public interface MetricsStorage {
     void saveRequestInfo(RequestInfo requestInfo);
     List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis,long endTimeInMillis  );
     Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMills, long endTimeInMills);

}
