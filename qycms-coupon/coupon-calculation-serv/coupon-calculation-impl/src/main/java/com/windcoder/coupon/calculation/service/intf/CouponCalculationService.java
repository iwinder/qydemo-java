package com.windcoder.coupon.calculation.service.intf;

import com.windcoder.coupon.calculation.api.beans.ShoppingCart;
import com.windcoder.coupon.calculation.api.beans.SimulationOrder;
import com.windcoder.coupon.calculation.api.beans.SimulationResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface CouponCalculationService {
    ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart);

    SimulationResponse simulateOrder(@RequestBody SimulationOrder cart);
}
