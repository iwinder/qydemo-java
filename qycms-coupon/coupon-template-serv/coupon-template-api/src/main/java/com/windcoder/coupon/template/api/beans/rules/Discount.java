package com.windcoder.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 使用优惠券的规则
 *  考虑到“折扣计算”相比“TemplateRule”是一个未来极有可能做花式扩展的结构，所以单独拉出来了一个Discount封装这种不确定性
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    // 满减 - 减掉的钱数，单位是 分，
    //  100 就代表 100 分，也就是一块钱。这比使用 Double 到处转换 BigDecimal 省了很多事儿
    // 折扣 - 90 = 9折，95 = 95折
    // 随机立减 - 最高的随机立减额
    // 晚间特别优惠券，日间优惠额，晚间优惠翻倍
    private Long quota;

    // 最低达到多少可以消费
    private Long threshold;
}
