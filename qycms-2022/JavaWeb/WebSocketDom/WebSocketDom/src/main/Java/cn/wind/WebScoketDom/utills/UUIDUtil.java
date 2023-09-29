package cn.wind.WebScoketDom.utills;

import java.util.UUID;

/**
 * Created by wind on 2016/11/20.
 */
public class UUIDUtil {
    public static String newUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }
}
