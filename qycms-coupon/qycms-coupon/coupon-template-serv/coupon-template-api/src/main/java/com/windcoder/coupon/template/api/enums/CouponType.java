package com.windcoder.coupon.template.api.enums;



import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 定义了多个不同类型的优惠券
 * “Unknown”类型的券，它专门用来对付故意输错 code 的恶意请求 ---“防御性编程”的思维
 */
@Getter
@AllArgsConstructor
public enum CouponType {
    UNKONW("unknown","0"),
    MONEY_OFF("满减券","1"),
    DISCOUNT("打折","2"),
    RANDOM_DISCOUNT("随机减","3"),
    LONELY_NIGHT_MONEY_OFF("寂寞午夜double券","4"),
    ANTI_PUA("PUA加倍奉还券","5");


    private String description;
    private String code;


    /**
     * 根据不同的编码返回对应的枚举对象
     * @param code
     * @return
     */
    public static CouponType convert(String code) {
        return Stream.of(values())
                .filter(couponType -> couponType.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(UNKONW);
    }

}
