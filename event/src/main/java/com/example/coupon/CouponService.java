package com.example.coupon;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Long save(final String content) {
        return couponRepository.save(new Coupon(CouponStatus.NOT_USE, content)).getId();
    }

    public void useImmediately(final Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        coupon.use();
        eventPublisher.publishEvent(new CouponUseEvent(coupon.getId()));
    }
}
