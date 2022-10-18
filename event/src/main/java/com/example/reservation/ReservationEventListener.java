package com.example.reservation;

import com.example.coupon.CouponUsedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationEventListener {

    private final ReservationService reservationService;

    @EventListener
    public void accept(final CouponUsedEvent couponUsedEvent) {
        reservationService.accept(couponUsedEvent.getCouponId());
    }
}
