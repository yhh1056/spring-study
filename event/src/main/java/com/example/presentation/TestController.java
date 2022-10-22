package com.example.presentation;

import static java.lang.Thread.sleep;

import com.example.coupon.CouponService;
import com.example.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final CouponService couponService;
    private final ReservationService reservationService;

    @GetMapping("/use")
    public void useCoupon() throws InterruptedException {
        Long couponId = couponService.save("hoho coupon");
        Long reservationId = reservationService.save(couponId);

        try {
            couponService.useImmediately(couponId);
        } catch (RuntimeException ignored) {}

        sleep(1000);
        String coupon = couponService.findById(couponId);
        String reservation = reservationService.findById(reservationId);
        log.info("coupon : {}", coupon);
        log.info("reservation : {}", reservation);
    }
}
