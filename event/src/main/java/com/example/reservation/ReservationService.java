package com.example.reservation;

import com.example.common.TransactionLogger;
import com.example.coupon.Coupon;
import com.example.coupon.CouponRepository;
import com.example.coupon.CouponStatus;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CouponRepository couponRepository;

    public Long save(final Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        return reservationRepository.save(new Reservation(coupon, ReservationStatus.WAITING)).getId();
    }

    public void accept(final Long couponId) {
        log.info("====ReservationService Accept Start====");
        TransactionLogger.logActualTransactionActive();
        Reservation reservations = reservationRepository.findByCoupon_IdAndCoupon_CouponStatus(couponId, CouponStatus.USED);
        reservations.use();
        log.info("====ReservationService Accept Finish====");
    }
}
