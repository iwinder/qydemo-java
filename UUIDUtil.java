package cn.zhonya.newyearsmailtarget.utils;

import java.util.UUID;

/**
 * UUID生成工具类
 * Created by wind on 2016/11/24.
 * http://windcoder.com
 */
public class UUIDUtil {
    public static String newUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }
}
