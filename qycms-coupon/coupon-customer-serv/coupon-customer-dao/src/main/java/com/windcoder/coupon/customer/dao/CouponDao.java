package com.windcoder.coupon.customer.dao;

import com.windcoder.coupon.customer.dao.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponDao extends JpaRepository<Coupon, Long> {
    long countByUserIdAndTemplateId(Long userId, Long templateId);
}
