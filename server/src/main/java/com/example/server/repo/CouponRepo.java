package com.example.server.repo;

import com.example.server.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, String> {

    void deleteByCoupon(String coupon);
}