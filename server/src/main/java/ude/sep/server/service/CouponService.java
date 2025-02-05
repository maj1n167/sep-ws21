package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ude.sep.server.model.Coupon;
import ude.sep.server.repo.CouponRepo;

import java.util.List;


@Service
public class CouponService {
    private final CouponRepo couponRepo;

    @Autowired
    public CouponService(CouponRepo couponRepo) {this.couponRepo = couponRepo;}

    public Coupon addCoupon(Coupon coupons){return couponRepo.save(coupons);}

    public List<Coupon> findAllCoupons(){
        return couponRepo.findAll();
    }

    public void deleteCoupon(int id){
        couponRepo.deleteById(id);
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
        System.out.println("Mail Sent...");

    }


}