package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupon {

    @Id
    String coupon;

    public Coupon(String coupon) {
        coupon = coupon;
    }

    public Coupon() { }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        coupon = coupon;
    }
}
