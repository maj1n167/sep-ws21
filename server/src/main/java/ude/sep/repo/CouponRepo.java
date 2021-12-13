package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
}