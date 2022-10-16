package com.example.coupon;

import lombok.Getter;

@Getter
public class CouponUseEvent {

    private final Long couponId;

    public CouponUseEvent(final Long couponId) {
        this.couponId = couponId;
    }
}
