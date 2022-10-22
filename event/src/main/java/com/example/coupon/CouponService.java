package com.example.coupon;

import com.example.common.TransactionLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public Long save(final String content) {
        return couponRepository.save(new Coupon(CouponStatus.NOT_USE, content)).getId();
    }

    @Transactional
    public void useImmediately(final Long couponId) {
        log.info("====CouponService UseImmediately Start====");
        log.info("Current Thread : {}", Thread.currentThread().getName());
        TransactionLogger.logActualTransactionActive();
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        coupon.use();
        log.info("====CouponService UseImmediately Finish====");
    }

    public String findById(final Long couponId) {
        return couponRepository.findById(couponId).orElseThrow().toString();
    }
}
