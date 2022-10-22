package com.example.reservation;

import com.example.common.TransactionLogger;
import com.example.coupon.Coupon;
import com.example.coupon.CouponRepository;
import com.example.coupon.CouponStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public Long save(final Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        return reservationRepository.save(new Reservation(coupon, ReservationStatus.WAITING)).getId();
    }

    @Transactional
    public void accept(final Long couponId) {
        log.info("====ReservationService Accept Start====");
        TransactionLogger.logActualTransactionActive();
        Reservation reservation = reservationRepository.findByCoupon_IdAndCoupon_CouponStatus(couponId, CouponStatus.USED);
        reservation.use();
        log.info("====ReservationService Accept Finish====");
    }

    @Transactional
    public void acceptFail(final Long couponId) {
        log.info("====ReservationService Accept Start====");
        TransactionLogger.logActualTransactionActive();
        Reservation reservation = reservationRepository.findByCoupon_IdAndCoupon_CouponStatus(couponId, CouponStatus.USED);
        reservation.use();
        log.info("====ReservationService Accept Finish====");
        throw new RuntimeException();
    }

    public String findById(final Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        return reservation.toString();
    }
}
