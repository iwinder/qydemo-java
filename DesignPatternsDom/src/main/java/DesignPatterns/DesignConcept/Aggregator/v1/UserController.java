package DesignPatterns.DesignConcept.Aggregator.v1;

import java.util.concurrent.TimeUnit;

public class UserController {
    private Metrics metrics = new Metrics();

    public UserController() {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }

    public void register(UserVo user) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamps("register", startTimestamp);
        // ...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", respTime);

    }


    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamps("login", startTimestamp);
        UserVo user = new UserVo();
        user.setTelephone(telephone);
        user.setPassword(password);
        // ...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login", respTime);
        return user;
    }
}
