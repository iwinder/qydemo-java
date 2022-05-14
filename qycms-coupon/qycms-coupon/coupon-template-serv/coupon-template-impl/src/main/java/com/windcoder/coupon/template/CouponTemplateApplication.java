package com.windcoder.coupon.template;

import com.windcoder.coupon.template.dao.entity.CouponTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@ComponentScan(basePackages = {"com.windcoder"})
public class CouponTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponTemplateApplication.class,args);
    }
}
