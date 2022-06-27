package com.windcoder.coupon.customer.event;

import com.windcoder.coupon.customer.api.beans.RequestCoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CouponProducer {
    @Autowired
    private StreamBridge streamBridge;

    public void sendCoupon(RequestCoupon coupon) {
        log.info("sent: {}", coupon);
        // streamBridge.send 方法的第一个参数是 Binding Name，它指定了这条消息要被发到哪一个信道中，
        // 其中 ADD_COUPON_EVENT=addCoupon-out-0，而 deleteCoupon=deleteCoupon-out-0。
        // 你先不要管这两个奇怪的值是什么，你只要把 Binding Name 理解成 一条消息从 Stream 到达 RabbitMQ 之间的“通道”，
        // 待会儿看到配置文件的时候，你就会清楚这条通道是怎么与 RabbitMQ 中定义的消息队列名称关联起来的了。
        streamBridge.send(EventConstant.ADD_COUPON_EVENT, coupon);
    }

    // 使用延迟消息发送
    public void sendCouponInDelay(RequestCoupon coupon) {
        log.info("sent: {}", coupon);
        streamBridge.send(EventConstant.ADD_COUPON_DELAY_EVENT,
                MessageBuilder.withPayload(coupon)
                        .setHeader("x-delay", 10 * 1000)
                        .build());
    }

    public void deleteCoupon(Long userId, Long couponId) {
        log.info("sent delete coupon event: userId={}, couponId={}", userId, couponId);
        streamBridge.send(EventConstant.DELETE_COUPON_EVENT, userId + "," + couponId);
    }
}
