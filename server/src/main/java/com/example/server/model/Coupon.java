package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupon {

    @Id
    String Coupon;

    public Coupon(String coupon) {
        Coupon = coupon;
    }

    public String getCoupon() {
        return Coupon;
    }

    public void setCoupon(String coupon) {
        Coupon = coupon;
    }
}
