package com.example.coupon;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Long save(final String content) {
        return couponRepository.save(new Coupon(CouponStatus.NOT_USE, content)).getId();
    }

    public void useImmediately(final Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        coupon.use();
    }
}
