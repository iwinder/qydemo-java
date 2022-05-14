package com.windcoder.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建优惠券模板 -- 分页
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedCouponTemplateInfo {
    private List<CouponTemplateInfo> templates;

    private int page;

    private long total;
}
