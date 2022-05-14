package com.windcoder.coupon.template.api.beans;

import com.windcoder.coupon.template.api.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 创建优惠券模板-- 封装tempalte信息
 * 封装了优惠券模板的基本信息，我们可以把优惠券模板当做一个“模具”，每一张优惠券都经由模具来制造
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {
    private Long id;
    @NotNull
    private String name;

    // 优惠券描述
    @NotNull
    private String desc;
    // 优惠券类型
    @NotNull
    private String type;
    // 适用门店 - 若无则全店通用
    private Long shopId;

    // 优惠券规则
    @NotNull
    private TemplateRule rule;
    private Boolean available;
}
