package com.example.reservation;

import com.example.common.EventFailRepository;
import com.example.common.FailEvent;
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
    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void accept(final CouponUsedEvent couponUsedEvent) {
        log.info("====ReservationEventListener Start====");
        log.info("Current Thread : {}", Thread.currentThread().getName());
        TransactionLogger.logActualTransactionActive();

        try {
            /**
             * 이벤트 실패시 다시 발행하려면 현재 메서드를 주석처리하고
             * fail메서드를 주석 해제한다.
             */
            reservationService.accept(couponUsedEvent.getCouponId());
//            fail(couponUsedEvent);
        } catch (RuntimeException ignored) {
            log.error("EVENT FAIL");
            EventFailRepository.add(new FailEvent(couponUsedEvent));
        }

        log.info("====ReservationEventListener Finish====");
    }

    private void fail(final CouponUsedEvent couponUsedEvent) {
        TransactionLogger.logActualTransactionActive();
        Reservation reservation = reservationRepository.findByCoupon_IdAndCoupon_CouponStatus(
                couponUsedEvent.getCouponId(), CouponStatus.USED);
        reservation.use();
        throw new RuntimeException();
    }
}
