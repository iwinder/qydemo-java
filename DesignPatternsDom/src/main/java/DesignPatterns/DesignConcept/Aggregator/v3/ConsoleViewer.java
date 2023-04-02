package DesignPatterns.DesignConcept.Aggregator.v3;

import DesignPatterns.DesignConcept.Aggregator.v2.RequestStat;
import Utills.PrintUtill;
import com.google.gson.Gson;

import java.util.Map;

public class ConsoleViewer implements StatViewer {
    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMillis) {
        PrintUtill.println("Time Span：[" + startTimeInMillis + "，" + endTimeInMillis + "]" );
        Gson gson = new Gson();
        PrintUtill.println(gson.toJson(requestStats));
    }
}
