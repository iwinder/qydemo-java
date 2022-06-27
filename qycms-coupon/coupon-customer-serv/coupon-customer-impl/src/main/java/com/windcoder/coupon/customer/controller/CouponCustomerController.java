package com.windcoder.coupon.customer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.windcoder.coupon.calculation.api.beans.ShoppingCart;
import com.windcoder.coupon.calculation.api.beans.SimulationOrder;
import com.windcoder.coupon.calculation.api.beans.SimulationResponse;
import com.windcoder.coupon.customer.api.beans.RequestCoupon;
import com.windcoder.coupon.customer.api.beans.SearchCoupon;
import com.windcoder.coupon.customer.dao.entity.Coupon;
import com.windcoder.coupon.customer.event.CouponProducer;
import com.windcoder.coupon.customer.service.intf.CouponCustomerService;
import com.windcoder.coupon.template.api.beans.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("coupon-customer")
@RefreshScope
public class CouponCustomerController {

    @Value("${disableCouponRequest:false}")
    private Boolean disableCoupon;

    @Autowired
    private CouponCustomerService customerService;

    @Autowired
    private CouponProducer couponProducer;

    @PostMapping("requestCoupon")
    @SentinelResource(value = "requestCoupon")
    public Coupon requestCoupon(@Valid @RequestBody RequestCoupon request) {
        if (disableCoupon) {
            log.info("暂停领取优惠券");
            return null;
        }
        return customerService.requestCoupon(request);
    }

    // 用户删除优惠券
    @DeleteMapping("deleteCoupon")
    public void deleteCoupon(@RequestParam("userId") Long userId,
                             @RequestParam("couponId") Long couponId) {
        customerService.deleteCoupon(userId, couponId);
    }

    // 用户模拟计算每个优惠券的优惠价格
    @PostMapping("simulateOrder")
    public SimulationResponse simulate(@Valid @RequestBody SimulationOrder order) {
        return customerService.simulateOrderPrice(order);
    }

    // ResponseEntity - 指定返回状态码 - 可以作为一个课后思考题
    @PostMapping("placeOrder")
    public ShoppingCart checkout(@Valid @RequestBody ShoppingCart info) {
        return customerService.placeOrder(info);
    }


    // 实现的时候最好封装一个search object类
    @PostMapping("findCoupon")
    @SentinelResource(value = "customer-findCoupon")
    public List<CouponInfo> findCoupon(@Valid @RequestBody SearchCoupon request) {
        return customerService.findCoupon(request);
    }


    // 用户获取优惠券
    @PostMapping("requestCouponEvent")
    public void requestCouponEvent(@Valid @RequestBody RequestCoupon request) {
        couponProducer.sendCoupon(request);
    }

    // 用户删除优惠券
    @DeleteMapping("deleteCouponEvent")
    public void deleteCouponEvent(@RequestParam("userId") Long userId,
                                  @RequestParam("couponId") Long couponId) {
        couponProducer.deleteCoupon(userId, couponId);
    }


    @PostMapping("requestCouponDelayEvent")
    public void requestCouponDelayedEvent(@Valid @RequestBody RequestCoupon request) {
        couponProducer.sendCouponInDelay(request);
    }
}
