package com.example.server.service;

import com.example.server.model.Coupon;
import com.example.server.model.Food;
import com.example.server.repo.CouponRepo;
import com.example.server.repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CouponService {
    private final CouponRepo couponRepo;

    @Autowired
    public CouponService(CouponRepo couponRepo) {
        this.couponRepo = couponRepo;
    }

    public Coupon addCoupon(Coupon coupons){
        return couponRepo.save(coupons);
    }

    public List<Coupon> findAllCoupons(){
        return couponRepo.findAll();
    }

    public Coupon updateCoupon(Coupon coupons){
        return couponRepo.save(coupons);
    }

    public void deleteCoupon(String coupon){
        couponRepo.deleteByCoupon(coupon);
    }


}