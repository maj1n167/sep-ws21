package com.example.server.controller;

import com.example.server.model.Coupon;
import com.example.server.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Component
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

    // Sending Coupon to Email
    @GetMapping("/send/{email}")
    public ResponseEntity<?> sendCoupon(@PathVariable("email") String email) {


        String currentEmail = email;
        Random rnd1 = new Random();
        int number1 = rnd1.nextInt(999999);
        Random rnd2 = new Random();
        int number2 = rnd2.nextInt(999999);
        String code;
        code = String.format("%06d", number1) + String.format("%06d", number2);
        Coupon coupon = new Coupon(code);
        couponService.addCoupon(coupon);
        couponService.sendEmail(currentEmail, "Ihr Rabattcode lautet: "+code, "Rabattcode");
        return new ResponseEntity<>(null, HttpStatus.OK);
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
