package com.example.coupon;

import com.example.common.TransactionLogger;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CouponService {

    private final CouponRepository couponRepository;

    public Long save(final String content) {
        return couponRepository.save(new Coupon(CouponStatus.NOT_USE, content)).getId();
    }

    public void useImmediately(final Long couponId) {
        log.info("====CouponService UseImmediately Start====");
        TransactionLogger.logActualTransactionActive();
        TransactionLogger.logActualTransactionActive();
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        coupon.use();
        log.info("====CouponService UseImmediately Finish====");
    }
}
