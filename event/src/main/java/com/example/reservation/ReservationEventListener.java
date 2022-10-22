package com.example.reservation;

import com.example.common.TransactionLogger;
import com.example.coupon.CouponStatus;
import com.example.coupon.CouponUsedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationEventListener {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @Async
    @TransactionalEventListener
    @Transactional
    public void accept(final CouponUsedEvent couponUsedEvent) {
        log.info("====ReservationEventListener Start====");
        log.info("Current Thread : {}", Thread.currentThread().getName());
        TransactionLogger.logActualTransactionActive();
//        reservationService.accept(couponUsedEvent.getCouponId());

        Reservation reservation = reservationRepository.findByCoupon_IdAndCoupon_CouponStatus(couponUsedEvent.getCouponId(), CouponStatus.USED);
        reservation.use();

        log.info("====ReservationEventListener Finish====");
    }
}
