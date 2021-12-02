package com.example.server.service;

import com.example.server.model.Coupon;
import com.example.server.repo.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String body,
                          String subject) {

        SimpleMailMessage message = new SimpleMailMessage();


        message.setFrom("suprmeorderingsystem@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");

    }


}