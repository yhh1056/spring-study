package com.example.reservation;

import com.example.common.TransactionLogger;
import com.example.coupon.CouponUsedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationEventListener {

    private final ReservationService reservationService;

    @EventListener
    public void accept(final CouponUsedEvent couponUsedEvent) {
        log.info("====ReservationEventListener Start====");
        TransactionLogger.logActualTransactionActive();
        reservationService.accept(couponUsedEvent.getCouponId());
        log.info("====ReservationEventListener Finish====");
    }
}
