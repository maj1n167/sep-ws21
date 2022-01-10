package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
}