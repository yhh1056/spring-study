package com.example.reservation;

import com.example.coupon.Coupon;
import com.example.coupon.CouponRepository;
import com.example.coupon.CouponStatus;
import com.example.coupon.CouponUseEvent;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CouponRepository couponRepository;

    public Long save(final Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        return reservationRepository.save(new Reservation(coupon, ReservationStatus.WAITING)).getId();
    }

    @EventListener
    public void accept(final CouponUseEvent couponUseEvent) {
        Long couponId = couponUseEvent.getCouponId();
        Reservation reservations = reservationRepository.findByCoupon_IdAndCoupon_CouponStatus(couponId, CouponStatus.USED);
        reservations.use();
    }
}
