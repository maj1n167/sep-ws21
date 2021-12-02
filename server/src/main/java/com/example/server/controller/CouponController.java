package com.example.server.controller;

import com.example.server.model.Coupon;
import com.example.server.model.Food;
import com.example.server.service.CouponService;
import com.example.server.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {

        List<Coupon> coupons = couponService.findAllCoupons();

        return new ResponseEntity<>(coupons, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupons) {
        Coupon newCoupon = couponService.addCoupon(coupons);
        return new ResponseEntity<>(newCoupon, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{coupon}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("coupon") String coupon) {
        couponService.deleteCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
