package com.windcoder.coupon.template.converter;

import com.windcoder.coupon.template.api.beans.CouponTemplateInfo;
import com.windcoder.coupon.template.dao.entity.CouponTemplate;

public class CouponTemplateConverter {
    public static CouponTemplateInfo convertToTemplateInfo(CouponTemplate template) {

        return CouponTemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .desc(template.getDescription())
                .type(template.getCategory().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();

    }
}
