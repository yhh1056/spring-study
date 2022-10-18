package com.example.reservation;

import com.example.coupon.CouponStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByCoupon_IdAndCoupon_CouponStatus(Long couponId, CouponStatus couponStatus);
}
