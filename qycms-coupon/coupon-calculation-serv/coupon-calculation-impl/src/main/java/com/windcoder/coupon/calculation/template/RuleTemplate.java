package com.windcoder.coupon.calculation.template;

import com.windcoder.coupon.calculation.api.beans.ShoppingCart;

public interface RuleTemplate {
    // 计算优惠券
    ShoppingCart calculate(ShoppingCart settlement);
}
